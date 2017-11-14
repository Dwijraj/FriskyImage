package com.example.a1405214.crazyimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dwijraj.FriskyAnim.FriskyBounce;
import dwijraj.FriskyAnim.FriskyFade;
import dwijraj.FriskyAnim.FriskyRain;
import dwijraj.FriskyAnim.FriskyTanslations;
import dwijraj.FriskyToast.FriskyToast;


public class MainActivity extends AppCompatActivity {

    private Button Fade;
    private Button SetImageRotating;
    private Button GetScaledImages;
    public  ImageView Image;
    private Button Base64Encode;
    private Button QRCodencode;
    private Button RotateBy;
    private Button GetScaledimage;
    private LinearLayout layout;
    public static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.call);


        GetScaledimage=(Button) findViewById(R.id.GetCustomScaledImage);
        GetScaledImages=(Button) findViewById(R.id.GetScaledImage);
        textView=(TextView) findViewById(R.id.TextView);
        Base64Encode=(Button) findViewById(R.id.Encode64);
        QRCodencode=(Button) findViewById(R.id.QRcodeencode);
        layout=(LinearLayout) findViewById(R.id.RootLayoutId);
        Image=(ImageView) findViewById(R.id.Image1);


        FriskyToast.warning(MainActivity.this,"Hello", Toast.LENGTH_SHORT).show();


        Fade=(Button) findViewById(R.id.fade);
        RotateBy=(Button) findViewById(R.id.RotateBy);
        Fade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Ids[]={R.drawable.barcode,R.drawable.call,R.drawable.car1,R.drawable.car2};
                Bitmap bitmap1[]=new Bitmap[4];

                bitmap1[0]=BitmapFactory.decodeResource(MainActivity.this.getResources(),Ids[0]);

                bitmap1[1]=BitmapFactory.decodeResource(MainActivity.this.getResources(),Ids[1]);

                bitmap1[2]=BitmapFactory.decodeResource(MainActivity.this.getResources(),Ids[2]);

                bitmap1[3]=BitmapFactory.decodeResource(MainActivity.this.getResources(),Ids[3]);


                new FriskyFade().InfiniteRepeat
                        (bitmap1,MainActivity.this,R.id.FadeLayout,5000);

            }
        });

        RotateBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Runnable runnable1=new Runnable() {
                    @Override
                    public void run() {

                        Image.animate().alpha(1).setDuration(3000).setInterpolator(new LinearInterpolator()).start();

                    }
                };


              Image.animate().alpha(0).setDuration(3000).setInterpolator(new LinearInterpolator()).withEndAction(runnable1).start();
            }
        });

        QRCodencode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
        GetScaledimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        GetScaledImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Image.setBackgroundColor(Color.BLACK);
            }
        });

        ((Button) findViewById(R.id.scaledImages)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        ((Button)findViewById(R.id.GetCustomScaledImage)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        ((Button) findViewById(R.id.RotateAntiClock)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FriskyTanslations Wheel1=new FriskyTanslations(MainActivity.this,R.id.Image1);
                FriskyTanslations Wheel2=new FriskyTanslations(MainActivity.this,R.id.Image2);
                Wheel1.StartRotationClockWise();
                Wheel2.StartRotationClockWise();
                FriskyTanslations Car=new FriskyTanslations(MainActivity.this,R.id.car);
                Car.MoveXBy(140,3000);
                Wheel1.MoveXBy(140,3000,true);
                Wheel2.MoveXBy(140,3000,true);


            }
        });
        findViewById(R.id.random_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FriskyBounce friskyBounce1=new FriskyBounce(MainActivity.this,R.id.RootId123,R.id.ID1234ID);
                FriskyBounce friskyBounce2=new FriskyBounce(MainActivity.this,R.id.RootId123,R.id.ID1235ID);
                FriskyBounce friskyBounce3=new FriskyBounce(MainActivity.this,R.id.RootId123,R.id.ID1236ID);
                friskyBounce1.StartCrazyBounce1(3000);
                friskyBounce2.StartCrazyBounce2(3000);
                friskyBounce3.StartBounce(5000,3000);

            }
        });

        ((Button) findViewById(R.id.Oscillate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        ((Button) findViewById(R.id.customOscillation)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        ((Button) findViewById(R.id.StopCrazyRotation)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        ((Button) findViewById(R.id.StopCrazyRotationAtCustomAngle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                           }
        });
        ((Button) findViewById(R.id.TranslateX)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        ((Button) findViewById(R.id.Bounce)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FriskyBounce friskyBounce=new FriskyBounce(MainActivity.this,R.id.RootId123,R.id.ID123ID);
                friskyBounce.StartCrazyBounce3(3000);


            }
        });

        findViewById(R.id.BlackandWhite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  }
        });

        findViewById(R.id.RainStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Integer> arrayList=new ArrayList<>();
                arrayList.add(R.id.rain1);
                arrayList.add(R.id.rain2);
                arrayList.add(R.id.rain3);
                arrayList.add(R.id.rain4);
                arrayList.add(R.id.rain5);
                arrayList.add(R.id.rain6);
                arrayList.add(R.id.rain7);
                arrayList.add(R.id.rain8);
                arrayList.add(R.id.rain9);
                arrayList.add(R.id.rain10);
                arrayList.add(R.id.rain11);
                arrayList.add(R.id.rain12);
                arrayList.add(R.id.rain13);
                arrayList.add(R.id.rain14);
                arrayList.add(R.id.rain15);
                arrayList.add(R.id.rain16);
                arrayList.add(R.id.rain17);
                arrayList.add(R.id.rain18);

                new FriskyRain().StartRain(arrayList,R.id.Root1235,-10,MainActivity.this);


            }
        });







    }
}
