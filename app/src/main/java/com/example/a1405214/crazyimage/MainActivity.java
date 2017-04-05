package com.example.a1405214.crazyimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button FixedRotatedImage;
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

        FixedRotatedImage=(Button) findViewById(R.id.RotateFixed);
        RotateBy=(Button) findViewById(R.id.RotateBy);
        FixedRotatedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        RotateBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        Base64Encode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



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

            }
        });







    }
}
