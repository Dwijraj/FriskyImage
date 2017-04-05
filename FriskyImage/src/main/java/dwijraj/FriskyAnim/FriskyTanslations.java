package dwijraj.FriskyAnim;

import android.app.Activity;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by 1405214 on 01-04-2017.
 */

public class FriskyTanslations extends FriskyRotating {

    protected View view;
    protected int viewId;
    public FriskyTanslations(Activity activity, int ViewId)
    {
        super(activity,ViewId);
        view=activity.findViewById(ViewId);
    }

    public void MoveXBy(float distance,long duration)
    {
       // this.StartRotationClockWise();

        final FriskyTanslations friskyTanslations1 =this;
        view.animate().translationXBy(distance).setInterpolator(new LinearInterpolator()).setDuration(duration).start();


    }
    public void MoveXBy(float distance,long duration,final boolean StopRotationAtCurrentAngle)
    {
        final FriskyTanslations friskyTanslations1 =this;
        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                    if(StopRotationAtCurrentAngle)
                    {
                        friskyTanslations1.StopCrazyRotationAtCurrentAngle();
                    }
                    else
                    {
                        friskyTanslations1.StopCrazyRotationAtAngle(0);

                    }


            }
        };
        view.animate().translationXBy(distance).setInterpolator(new LinearInterpolator()).withEndAction(runnable).setDuration(duration).start();


    }

}
