package dwijraj.FriskyRotation;

import android.app.Activity;
import android.widget.ImageView;

/**
 * Created by 1405214 on 28-03-2017.
 */

public class FriskyRotateImageViewBy {

    private Activity Activity;
    private float RotationBy;
    private ImageView imageView;

   protected FriskyRotateImageViewBy()
   {

   }

    public FriskyRotateImageViewBy(Activity activity, int ImageViewId, float RotateAngle)
    {
        this.Activity=activity;
        this.RotationBy=RotateAngle;
        imageView=(ImageView) activity.findViewById(ImageViewId);
        imageView.setRotation(this.RotationBy);
    }

}
