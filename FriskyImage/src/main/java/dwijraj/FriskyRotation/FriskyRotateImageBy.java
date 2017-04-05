package dwijraj.FriskyRotation;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by 1405214 on 28-03-2017.
 */

public class FriskyRotateImageBy {

    protected Bitmap bitmap;
    protected int  RotationBy;
    public FriskyRotateImageBy(Bitmap bitmap, int rotateAngle)
    {
        this.bitmap=bitmap;
        RotationBy=rotateAngle;
    }

    public Bitmap GetRotatedImageBy()
    {
        Bitmap Rotated_Bitmap,Original_bitmap;

        Original_bitmap=this.bitmap;

        Matrix matrix = new Matrix();
        matrix.postRotate(RotationBy);


        Rotated_Bitmap = Bitmap.createBitmap(Original_bitmap, 0, 0, Original_bitmap.getWidth(), Original_bitmap.getHeight(),
                matrix, true);

        return Rotated_Bitmap;
    }


}
