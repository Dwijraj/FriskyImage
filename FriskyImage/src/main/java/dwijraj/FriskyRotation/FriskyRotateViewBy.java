package dwijraj.FriskyRotation;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 1405214 on 28-03-2017.
 */

public class FriskyRotateViewBy {

    private Activity Activity;
    private float RotationBy;
    private View imageView;

   protected FriskyRotateViewBy()
   {

   }

    public FriskyRotateViewBy(Activity activity, int ImageViewId, float RotateAngle)
    {
        this.Activity=activity;
        this.RotationBy=RotateAngle;
        imageView=activity.findViewById(ImageViewId);
        imageView.setRotation(this.RotationBy);
    }

}
