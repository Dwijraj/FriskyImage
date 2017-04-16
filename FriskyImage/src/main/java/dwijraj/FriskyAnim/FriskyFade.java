package dwijraj.FriskyAnim;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by 1405214 on 16-04-2017.
 */

public  class FriskyFade {


    private  Runnable runnable1,runnable2;
    private  int LAST_FLAG_VALUE;
    private  int FLAG;//=0;
    private  static Drawable drawable[];
    private  View view;

    public FriskyFade()
    {
        this.FLAG=0;
    }



    public  void StopOnLastElement(Drawable drawables[],Activity activity,int IdOfView,int Duration)
    {

        LAST_FLAG_VALUE=drawables.length;
        view=activity.findViewById(IdOfView);
        drawable=new Drawable[drawables.length];
        StopOnLastElementExecute(drawable,Duration);
    }

    public  void StopOnLastElement(Bitmap bitmap[], Activity activity, int IdOfView, final int Duration)
    {
        LAST_FLAG_VALUE=bitmap.length;
        view=activity.findViewById(IdOfView);
        drawable=new Drawable[bitmap.length];
        for (int i=0;i<bitmap.length;i++)
        {
            drawable[i]=new BitmapDrawable(activity.getResources(),bitmap[i]);
        }
        StopOnLastElementExecute(drawable,Duration);
    }


    public  void InfiniteRepeat(Drawable drawables[],Activity activity,int IdOfView,int Duration)
    {

        LAST_FLAG_VALUE=drawables.length;
        view=activity.findViewById(IdOfView);
        drawable=new Drawable[drawables.length];
        startFade(drawable,Duration);
    }

    public  void InfiniteRepeat(Bitmap bitmap[], Activity activity, int IdOfView, final int Duration)
    {
        LAST_FLAG_VALUE=bitmap.length;
        view=activity.findViewById(IdOfView);
        drawable=new Drawable[bitmap.length];
        for (int i=0;i<bitmap.length;i++)
        {
            drawable[i]=new BitmapDrawable(activity.getResources(),bitmap[i]);
        }
        startFade(drawable,Duration);
    }
    private  void startFade(final Drawable drawable[],final int Duration)
    {

            runnable2=new Runnable() {
                @Override
                public void run() {
                    view.animate().alpha(0).setDuration(Duration).withEndAction(runnable1).setInterpolator(new LinearInterpolator()).start();

                }
            };
            runnable1=new Runnable() {
                @Override
                public void run() {

                    FLAG=FLAG+1;
                    if(FLAG==LAST_FLAG_VALUE)
                    {
                        FLAG=0;
                    }
                    view.setBackground(drawable[FLAG]);
                    view.setAlpha(0);
                    view.animate().alpha(1).setDuration(Duration).withEndAction(runnable2).setInterpolator(new LinearInterpolator()).start();

                }
            };
            view.animate().alpha(0).setDuration(Duration).withEndAction(runnable1).setInterpolator(new LinearInterpolator()).start();

    }

    private  void StopOnLastElementExecute(final Drawable drawable[],final int Duration)
    {


      //  view.setBackground(drawable[0]);
        runnable2=new Runnable() {
            @Override
            public void run() {
                view.animate().alpha(0).setDuration(Duration).withEndAction(runnable1).setInterpolator(new LinearInterpolator()).start();

            }
        };
        runnable1=new Runnable() {
            @Override
            public void run() {

                FLAG=FLAG+1;
                if(FLAG!=LAST_FLAG_VALUE)
                {
                    view.setBackground(drawable[FLAG]);
                    view.setAlpha(0);
                    view.animate().alpha(1).setDuration(Duration).withEndAction(runnable2).setInterpolator(new LinearInterpolator()).start();

                }
                else {
                    FLAG=0;
                }

            }
        };
        view.animate().alpha(0).setDuration(Duration).withEndAction(runnable1).setInterpolator(new LinearInterpolator()).start();

    }

}
