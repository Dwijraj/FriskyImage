package dwijraj.FriskyAnim;

import android.app.Activity;
import android.view.View;
import android.view.animation.LinearInterpolator;

import dwijraj.FriskyRotation.FriskyRotateImageViewBy;

/**
 * Created by 1405214 on 29-03-2017.
 */

public class FriskyRotating extends FriskyRotateImageViewBy {

    protected View imageView;
    protected Runnable runnable2;
    protected Runnable runnable1;
    public FriskyRotating(Activity activity, int id)
    {

        imageView=   activity.findViewById(id);

    }
    public void StartRotationClockWise()
    {
         runnable1 = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy(360).withEndAction(this).setDuration(3000).setInterpolator(new LinearInterpolator()).start();
            }
        };

        imageView.animate().rotationBy(360).withEndAction(runnable1).setDuration(3000).setInterpolator(new LinearInterpolator()).start();

    }
    public void StartRotationAntiClockWise()
    {
         runnable1 = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy(-360).withEndAction(this).setDuration(3000).setInterpolator(new LinearInterpolator()).start();
            }
        };

        imageView.animate().rotationBy(-360).withEndAction(runnable1).setDuration(3000).setInterpolator(new LinearInterpolator()).start();
    }

    public void Oscillation()
    {
         runnable1 = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy(180).withEndAction(runnable2).setDuration(3000).setInterpolator(new LinearInterpolator()).start();
            }
        };

         runnable2 = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy(-180).withEndAction(runnable1).setDuration(3000).setInterpolator(new LinearInterpolator()).start();
            }
        };

        imageView.animate().rotationBy(-90).withEndAction(runnable1).setDuration(3000).setInterpolator(new LinearInterpolator()).start();

    }
    public void CustomOscillation(int startAngle, final int MaxAngleOfRotation,final int TimeToReachStartAngle,final int TimePeriodOfOscillation)
    {
         runnable1 = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy((-1)*MaxAngleOfRotation).withEndAction(runnable2).setDuration(TimePeriodOfOscillation).setInterpolator(new LinearInterpolator()).start();
            }
        };

        runnable2 = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy(MaxAngleOfRotation).withEndAction(runnable1).setDuration(TimePeriodOfOscillation).setInterpolator(new LinearInterpolator()).start();
            }
        };

        imageView.animate().rotationBy(startAngle).withEndAction(runnable1).setDuration(TimeToReachStartAngle).setInterpolator(new LinearInterpolator()).start();

    }
    public void StopCrazyRotationAtCurrentAngle()
    {
        runnable1 = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy(0).withEndAction(runnable2).setDuration(0).setInterpolator(new LinearInterpolator()).start();
            }
        };

        runnable2 = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy(0).withEndAction(runnable1).setDuration(0).setInterpolator(new LinearInterpolator()).start();
            }
        };

        imageView.animate().rotationBy(0).withEndAction(runnable1).setDuration(0).setInterpolator(new LinearInterpolator()).start();

    }
    public void StopCrazyRotationAtAngle(int StoppingAngle)
    {
        imageView.animate().rotationBy(-imageView.getRotation()+StoppingAngle).setDuration(0).setInterpolator(new LinearInterpolator()).start();

    }

}
