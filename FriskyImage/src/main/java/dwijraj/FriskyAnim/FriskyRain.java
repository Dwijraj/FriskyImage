package dwijraj.FriskyAnim;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * Created by 1405214 on 02-04-2017.
 */

public class FriskyRain {

    private Activity activity;
    private Runnable runnable1[];
    private Runnable runnable2[];
    private float PosX[];
    private float PosY[];
    public FriskyRain(Activity activity)
    {
        this.activity=activity;
    }
    public void StartRain(ArrayList<Integer> ViewIds, int ParentViewID, final float RainDirection)
    {

        int length=ViewIds.size()-1;

        final  View ParentView=activity.findViewById(ParentViewID);
        ArrayList<View> Views=new ArrayList<>(length+1);

        runnable1=new Runnable[length+1];
        runnable2=new Runnable[length+1];

        PosX=new float[length+1];
        PosY=new float[length+1];

        for(int i=0;i<=length;i++)
        {
            View v;//=Views.get(i);
            v =activity.findViewById(ViewIds.get(i));
            Views.add(v);

            PosX[i]=v.getX();
            PosY[i]=v.getY();
        }
        ViewGroup.MarginLayoutParams marginLayoutParams=(ViewGroup.MarginLayoutParams) ParentView.getLayoutParams();
        final   float MarginTop=marginLayoutParams.topMargin;
        final   float MarginBottom=marginLayoutParams.bottomMargin;
        final   float MarginLeft=marginLayoutParams.leftMargin;
        final   float MarginRight=marginLayoutParams.rightMargin;

     //   Log.v("TAG",view.getId()+",");

        for(int i=0;i<=length;i++) {
            final int a = i;

            final View view1 = Views.get(i);




            Log.v("TAGViewIDs", view1.getId() + ",");

              runnable2[i]=new Runnable() {
                @Override
                public void run() {

                    view1.setVisibility(View.VISIBLE);
                    view1.animate().translationXBy(RainDirection).translationYBy((ParentView.getY()+ParentView.getHeight()-MarginBottom)-view1.getY()).setDuration(3000).withEndAction(runnable1[a]).setInterpolator(new LinearInterpolator()).start();

                }
            };
            runnable1[i]=new Runnable() {
                @Override
                public void run() {

              //      view1.setVisibility(View.INVISIBLE);
                    view1.animate().x(PosX[a]).y(PosY[a]).setDuration(0).withEndAction(runnable2[a]).setInterpolator(new LinearInterpolator()).start();
                }
            };
            view1.animate().translationXBy(RainDirection).translationYBy((ParentView.getY()+ParentView.getHeight()-MarginBottom)-view1.getY()).setDuration(3000).withEndAction(runnable1[i]).setInterpolator(new LinearInterpolator()).start();

        }









    }

}
