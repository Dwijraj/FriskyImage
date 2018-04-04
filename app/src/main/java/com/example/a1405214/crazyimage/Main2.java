package com.example.a1405214.crazyimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import dwijraj.FriskyAnim2.viewanimator.ViewAnimator;
import dwijraj.FriskySpeedo.speedviewlib.ProgressiveGauge;
import dwijraj.FriskySpeedo.speedviewlib.SpeedView;
import dwijraj.FriskyToolTip.ViewTooltip;

public class Main2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*SpeedView sp= (SpeedView) findViewById(R.id.SpeedView);
        sp.speedTo(80,4000); */

        //ProgressiveGauge progressiveGauge=(ProgressiveGauge) findViewById(R.id.Gauge);
        //progressiveGauge.speedTo(80,4000);

        /*final EditText editText=(EditText) findViewById(R.id.Edittext);
        Button   OK=(Button) findViewById(R.id.Button);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewTooltip
                        .on(Main2.this, editText)
                        .autoHide(true, 1000)
                        .corner(30)
                        .position(ViewTooltip.Position.LEFT)
                        .text(editText.getText().toString())
                        .show();
            }
        }); */

        ImageView image=(ImageView) findViewById(R.id.image1);
        TextView text=(TextView) findViewById(R.id.Text1);


        ViewAnimator
                .animate(image)
                .translationY(-1000, 0)
                .alpha(0,1)
                .andAnimate(text)
                .dp().translationX(-20, 0)
                .decelerate()
                .duration(2000)
                .thenAnimate(image)
                .scale(1f, 0.5f, 1f)
                .accelerate()
                .duration(1000)
                .start();

    }
}
