package dwijraj.FriskyShapes;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;

/**
 * Created by 1405214 on 28-03-2017.
 */

public abstract class FriskyImageResize {

    private static Activity activity;
    public static Bitmap[] FixscaledImages(Bitmap bitmap)
    {
        Bitmap Scaled_Bitmap[]=new Bitmap[4];

        int Image_Width=bitmap.getWidth();
        int image_height=bitmap.getHeight();


        Scaled_Bitmap[0]=Bitmap.createScaledBitmap(bitmap,150,150,false);
        Scaled_Bitmap[1]=Bitmap.createScaledBitmap(bitmap,350,350,false);
        Scaled_Bitmap[2]=Bitmap.createScaledBitmap(bitmap,250,250,false);
        Scaled_Bitmap[3]=Bitmap.createScaledBitmap(bitmap,50,50,false);



        return Scaled_Bitmap;

    }
    public static  Bitmap ResizeImage(Bitmap bitmap,int Width,int Height)
    {
        Bitmap bitmap1=bitmap;

        bitmap1=Bitmap.createScaledBitmap(bitmap,Width,Height,false);

        return bitmap1;

    }

    public static Bitmap GetScaledImage(Bitmap bitmap,int ScaleX,int ScaleY )
    {
        Bitmap result=null;

        int Image_Width=bitmap.getWidth();
        int image_height=bitmap.getHeight();

        if(ScaleX>Image_Width||ScaleY>image_height)
        {
            return null;
        }
        else
        {
            int ScaledX=Image_Width/ScaleX;
            int ScaledY=image_height/ScaleY;

            result= Bitmap.createScaledBitmap(bitmap,ScaledX,ScaledY,false);

            return result;
        }


    }
    public static Bitmap  CrazycompressImage(String imageUri,Activity activity1,float MaxHeight,float MaxWidth) {

        activity=activity1;
        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//		by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//		you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//		max Height and width values of the compressed image is taken as 816x612

        float maxHeight = MaxHeight;
        float maxWidth = MaxWidth;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//		width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) { 				imgRatio = maxHeight / actualHeight; 				actualWidth = (int) (imgRatio * actualWidth); 				actualHeight = (int) maxHeight; 			} else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//		setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//		inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//		this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//			load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight,Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//		check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
            } else if (orientation == 3) {
                matrix.postRotate(180);
            } else if (orientation == 8) {
                matrix.postRotate(270);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return scaledBitmap;

    }
    private  static String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = activity.getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {

            inSampleSize++;
        }

        return inSampleSize;
    }


}
