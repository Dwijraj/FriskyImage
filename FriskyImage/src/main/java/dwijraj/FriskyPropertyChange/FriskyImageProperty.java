package dwijraj.FriskyPropertyChange;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by 1405214 on 29-03-2017.
 */

public abstract class  FriskyImageProperty {


    public static  Bitmap FriskyEnhanceImage(Bitmap mBitmap, float contrast, float brightness) {
        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, brightness,
                        0, contrast, 0, 0, brightness,
                        0, 0, contrast, 0, brightness,
                        0, 0, 0, 1, 0
                });
        Bitmap BrightedImage = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), mBitmap
                .getConfig());
        Canvas canvas = new Canvas(BrightedImage);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        return BrightedImage;
    }

    public static Bitmap FriskyContrast(Bitmap bitmap,int Value)
    {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[] {
                Value, 0, 0, 0, 1,
                0, Value, 0, 0, 1,
                0, 0, Value, 0, 1,
                0, 0, 0, Value, 0   });

        Bitmap BrightedImage = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap
                .getConfig());
        Canvas canvas = new Canvas(BrightedImage);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return BrightedImage;

    }


    public static   Bitmap FriskyBright(Bitmap mBitmap,int fb) {
        ColorMatrix colorMatrix = new ColorMatrix();
         colorMatrix.set(new float[] {
                1, 0, 0, 0, fb,
                0, 1, 0, 0, fb,
                0, 0, 1, 0, fb,
                0, 0, 0, 1, 0   });

        Bitmap BrightedImage = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), mBitmap
                .getConfig());
        Canvas canvas = new Canvas(BrightedImage);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        return BrightedImage;

    }
    public static    Bitmap FriskyBlackandWhite(Bitmap src){

        Bitmap dest = Bitmap.createBitmap(
                src.getWidth(), src.getHeight(), src.getConfig());

        for(int x = 0; x < src.getWidth(); x++){
            for(int y = 0; y < src.getHeight(); y++){
                int pixelColor = src.getPixel(x, y);
                int pixelAlpha = Color.alpha(pixelColor);
                int pixelRed = Color.red(pixelColor);
                int pixelGreen = Color.green(pixelColor);
                int pixelBlue = Color.blue(pixelColor);

                int pixelBW = (pixelRed + pixelGreen + pixelBlue)/3;
                int newPixel = Color.argb(
                        pixelAlpha, pixelBW, pixelBW, pixelBW);

                dest.setPixel(x, y, newPixel);
            }
        }
        return dest;
    }

}
