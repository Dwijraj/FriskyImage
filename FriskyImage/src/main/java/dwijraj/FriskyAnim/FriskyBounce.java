package dwijraj.FriskyAnim;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;


import static java.lang.Math.abs;

/**
 * Created by 1405214 on 29-03-2017.
 */

public class FriskyBounce {

    private View View;
    private Activity activity;
    private View Rootview;
    private Runnable runnable1;
    private Runnable runnable2;
    private Runnable runnable3;
    private Runnable runnable4;
    private Runnable runnable5;
    private Rect ViewRect;
    private Rect RootLayoutRect;
    private static float DP;
    private static String FLAG1 = "RIGHT";
    private static String FLAG2 = "RIGHT";

    public FriskyBounce(Activity activity, int RootLayourId, int TranslatingViewId) {
        this.activity = activity;
        View = (View) activity.findViewById(TranslatingViewId);
        this.Rootview = activity.findViewById(RootLayourId);
        DP = activity.getResources().getDisplayMetrics().density;
        ViewRect = this.locateView(activity.findViewById(TranslatingViewId));
        RootLayoutRect = this.locateView(activity.findViewById(RootLayourId));

    }

    /*public void StartCrazyBounce1() {



        int a[]=new int[2];
        view.getLocationOnScreen(a);

        final float PosX=View.getX();
        final float PosY=View.getY();


        runnable5=new Runnable() {
            @Override
            public void run() {

                View.animate().x(0).y(abs(view.getHeight()/(2*DP))).withEndAction(runnable2).withEndAction(runnable2).setDuration(5000).setInterpolator(new LinearInterpolator()).start();

            }
        };


        runnable4=new Runnable() {
            @Override
            public void run() {
                View.animate().x(PosX).y(PosY).withEndAction(runnable5).setDuration(5000).setInterpolator(new LinearInterpolator()).start();

            }
        };

       runnable3=new Runnable() {
            @Override
            public void run() {

                View.animate().translationXBy(PosX).y(PosY/2).withEndAction(runnable4).setInterpolator(new LinearInterpolator()).start();


            }
        };

        runnable2=new Runnable() {
            @Override
            public void run() {


                int a=RootLayoutRect.right+RootLayoutRect.left/2;
                View.animate().y(0).x(PosX).withEndAction(runnable3).setDuration(5000).setInterpolator(new LinearInterpolator()).start();
            }
        };
        View.animate().x(0).y(PosY/2).withEndAction(runnable2).setDuration(3000).setInterpolator(new LinearInterpolator()).start();

    }
    public void StartCrazyBounce2()
    {


        int a[]=new int[2];
        view.getLocationOnScreen(a);

        final float PosX=View.getX();
        final float PosY=View.getY();


        runnable5=new Runnable() {
            @Override
            public void run() {

                View.animate().translationXBy(PosX).y(PosY/2).withEndAction(runnable2).setDuration(3000).setInterpolator(new LinearInterpolator()).start();

            }
        };


        runnable4=new Runnable() {
            @Override
            public void run() {
                View.animate().x(PosX).y(PosY).withEndAction(runnable5).setDuration(5000).setInterpolator(new LinearInterpolator()).start();

            }
        };

        runnable3=new Runnable() {
            @Override
            public void run() {

                View.animate().translationXBy(-PosX).y(PosY/2).withEndAction(runnable4).setInterpolator(new LinearInterpolator()).start();


            }
        };

        runnable2=new Runnable() {
            @Override
            public void run() {


                int a=RootLayoutRect.right+RootLayoutRect.left/2;
                View.animate().y(0).x(PosX).withEndAction(runnable3).setDuration(5000).setInterpolator(new LinearInterpolator()).start();
            }
        };
        View.animate().translationXBy(PosX).y(PosY/2).withEndAction(runnable2).setDuration(3000).setInterpolator(new LinearInterpolator()).start();

    }*/
    public void StartCrazyBounce1(final int TimeForCover) {


        int a[] = new int[2];
        Rootview.getLocationOnScreen(a);

        final float PosX = View.getX();
        final float PosY = View.getY();


        runnable5 = new Runnable() {
            @Override
            public void run() {

                View.animate().x(0).y(PosY / 2).withEndAction(runnable2).setDuration(TimeForCover).withEndAction(runnable2).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();

            }
        };


        runnable4 = new Runnable() {
            @Override
            public void run() {
                View.animate().x(PosX).y(PosY).withEndAction(runnable5).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();

            }
        };

        runnable3 = new Runnable() {
            @Override
            public void run() {

                View.animate().translationXBy(PosX).y(PosY / 2).setDuration(TimeForCover).withEndAction(runnable4).setInterpolator(new LinearInterpolator()).start();


            }
        };

        runnable2 = new Runnable() {
            @Override
            public void run() {


                int a = RootLayoutRect.right + RootLayoutRect.left / 2;
                View.animate().y(0).x(PosX).withEndAction(runnable3).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();
            }
        };
        View.animate().x(0).y(PosY / 2).withEndAction(runnable2).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();

    }

    public void Dribble(final int DribbleTime) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            runnable2 = new Runnable() {
                @Override
                public void run() {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        View.animate().translationZBy(-100).setDuration(DribbleTime).withEndAction(runnable1).setInterpolator(new LinearInterpolator()).start();

                    }

                }
            };
            runnable1 = new Runnable() {
                @Override
                public void run() {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        View.animate().translationZBy(100).setDuration(DribbleTime).withEndAction(runnable2).setInterpolator(new LinearInterpolator()).start();

                    }

                }
            };
            runnable1 = new Runnable() {
                @Override
                public void run() {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        View.animate().translationZBy(100).setDuration(DribbleTime).withEndAction(runnable2).setInterpolator(new LinearInterpolator()).start();

                    }

                }
            };
            View.animate().translationZBy(-1000).setDuration(DribbleTime).withEndAction(runnable1).setInterpolator(new LinearInterpolator()).start();

        } else {
            // do something for phones running an SDK before lollipop
        }

    }

    public void StartCrazyBounce2(final int TimeForCover) {
        int a[] = new int[2];
        Rootview.getLocationOnScreen(a);

        final float PosX = View.getX();
        final float PosY = View.getY();


        runnable5 = new Runnable() {
            @Override
            public void run() {

                View.animate().translationXBy(PosX).y(PosY / 2).withEndAction(runnable2).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();

            }
        };


        runnable4 = new Runnable() {
            @Override
            public void run() {
                View.animate().x(PosX).y(PosY).withEndAction(runnable5).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();

            }
        };

        runnable3 = new Runnable() {
            @Override
            public void run() {

                View.animate().translationXBy(-PosX).y(PosY / 2).setDuration(TimeForCover).withEndAction(runnable4).setInterpolator(new LinearInterpolator()).start();


            }
        };

        runnable2 = new Runnable() {
            @Override
            public void run() {


                int a = RootLayoutRect.right + RootLayoutRect.left / 2;
                View.animate().y(0).x(PosX).withEndAction(runnable3).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();
            }
        };
        View.animate().translationXBy(PosX).y(PosY / 2).withEndAction(runnable2).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();

    }

    public void StartCrazyBounce3(final int TimeForCover)
    {
        int a[] = new int[2];
        Rootview.getLocationOnScreen(a);

        final float PosX = View.getX();
        final float PosY = View.getY();

        final Runnable runnable9=new Runnable() {
            @Override
            public void run() {

                View.animate().x(0).y(PosY / 2).withEndAction(runnable2).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();

            }
        };


        final Runnable runnable8=new Runnable() {
            @Override
            public void run() {

                View.animate().x(PosX).y(PosY).withEndAction(runnable9).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();

            }
        };


        final Runnable runnable7=new Runnable() {
            @Override
            public void run() {

                View.animate().translationXBy(-PosX).y(PosY / 2).setDuration(TimeForCover).withEndAction(runnable8).setInterpolator(new LinearInterpolator()).start();

            }
        };
       final Runnable runnable6=new Runnable() {
            @Override
            public void run() {
                int a = RootLayoutRect.right + RootLayoutRect.left / 2;
                View.animate().y(0).x(PosX).withEndAction(runnable7).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();

            }
        };

        runnable5 = new Runnable() {
            @Override
            public void run() {

                View.animate().translationXBy(PosX).y(PosY / 2).withEndAction(runnable6).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();

            }
        };


        runnable4 = new Runnable() {
            @Override
            public void run() {
                View.animate().x(PosX).y(PosY).withEndAction(runnable5).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();

            }
        };

        runnable3 = new Runnable() {
            @Override
            public void run() {

                View.animate().translationXBy(PosX).y(PosY / 2).setDuration(TimeForCover).withEndAction(runnable4).setInterpolator(new LinearInterpolator()).start();


            }
        };

        runnable2 = new Runnable() {
            @Override
            public void run() {


                int a = RootLayoutRect.right + RootLayoutRect.left / 2;
                View.animate().y(0).x(PosX).withEndAction(runnable3).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();
            }
        };
        View.animate().x(0).y(PosY / 2).withEndAction(runnable2).setDuration(TimeForCover).setInterpolator(new LinearInterpolator()).start();


    }
   public void StartBounce(final int TimeToTravelUp,final int TimeToTravelBottom)
   {

       ViewGroup.MarginLayoutParams marginLayoutParams=(ViewGroup.MarginLayoutParams) this.Rootview.getLayoutParams();
    final   float PosX=View.getX();
    final   float MarginTop=marginLayoutParams.topMargin;
    final   float MarginBottom=marginLayoutParams.bottomMargin;
    final   float MarginLeft=marginLayoutParams.leftMargin;
    final   float MarginRight=marginLayoutParams.rightMargin;

       runnable3=new Runnable() {
           @Override
           public void run() {
               if(View.getX()>Rootview.getX()+Rootview.getWidth()-MarginLeft)
               {
                   FLAG1="LEFT";
                   View.animate().translationXBy(-60*DP).y((Rootview.getY()+Rootview.getHeight()-MarginBottom-View.getHeight())).withEndAction(runnable2).setDuration(TimeToTravelBottom).setInterpolator(new LinearInterpolator()).start();

               }
               else if(View.getX()<Rootview.getX())
               {
                   FLAG1="RIGHT";
                   View.animate().translationXBy(60*DP).y((Rootview.getY()+Rootview.getHeight()-MarginBottom-View.getHeight())).withEndAction(runnable2).setDuration(TimeToTravelBottom).setInterpolator(new LinearInterpolator()).start();

               }
               else
               {
                   if(FLAG1.equals("LEFT"))
                   {
                       View.animate().translationXBy(-60*DP).y((Rootview.getY()+Rootview.getHeight()-MarginBottom-View.getHeight())).withEndAction(runnable2).setDuration(TimeToTravelBottom).setInterpolator(new LinearInterpolator()).start();

                   }
                   else
                   {
                       View.animate().translationXBy(60*DP).y((Rootview.getY()+Rootview.getHeight()-MarginBottom-View.getHeight())).withEndAction(runnable2).setDuration(TimeToTravelBottom).setInterpolator(new LinearInterpolator()).start();

                   }

               }
           }
       };

       runnable2=new Runnable() {
           @Override
           public void run() {

              // View.animate().translationXBy(60*DP).y((Rootview.getY()+Rootview.getHeight()-MarginBottom-View.getHeight())).withEndAction(runnable2).setDuration(3000).setInterpolator(new LinearInterpolator()).start();

               if((View.getX()+View.getWidth())>Rootview.getX()+Rootview.getWidth()-MarginLeft)
               {
                   FLAG1="LEFT";
                   View.animate().y(0).translationXBy(-60*DP).withEndAction(runnable3).setDuration(TimeToTravelUp).setInterpolator(new LinearInterpolator()).start();

               }
               else if(View.getX()<Rootview.getX())
               {
                   FLAG1="RIGHT";
                   View.animate().y(0).translationXBy(60*DP).withEndAction(runnable3).setDuration(TimeToTravelUp).setInterpolator(new LinearInterpolator()).start();

               }
               else
               {
                   if(FLAG1.equals("LEFT"))
                   {
                       View.animate().y(0).translationXBy(-60*DP).withEndAction(runnable3).setDuration(TimeToTravelUp).setInterpolator(new LinearInterpolator()).start();

                   }
                   else
                   {
                       View.animate().y(0).translationXBy(60*DP).withEndAction(runnable3).setDuration(TimeToTravelUp).setInterpolator(new LinearInterpolator()).start();

                   }

               }

           }
       };
       this.View.animate().y((Rootview.getY()+Rootview.getHeight()-MarginBottom-View.getHeight())).withEndAction(runnable2).setDuration(TimeToTravelBottom).setInterpolator(new LinearInterpolator()).start();
   }


    private Rect locateView(View view) {
        Rect loc = new Rect();
        int[] location = new int[2];

        view.getLocationOnScreen(location);

        loc.left = location[0];
        loc.top = location[1];
        loc.right = loc.left + view.getWidth();
        loc.bottom = loc.top + view.getHeight();
        return loc;
    }






}
