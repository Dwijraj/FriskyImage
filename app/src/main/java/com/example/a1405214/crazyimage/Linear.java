package com.example.a1405214.crazyimage;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Linear extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        LinearLayout parent =(LinearLayout) findViewById(R.id.LL);

        parent.setBackgroundColor(Color.BLACK);
        parent.setOrientation(LinearLayout.VERTICAL);

        ImageView imageView=new ImageView(this);
        imageView.setBackgroundColor(Color.WHITE);
        parent.addView(imageView);

    /*    parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        parent.setOrientation(LinearLayout.HORIZONTAL);

//children of parent linearlayout

        ImageView iv = new ImageView(Linear.this);

        LinearLayout layout2 = new LinearLayout(Linear.this);

        layout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        parent.setOrientation(LinearLayout.VERTICAL);

        parent.addView(iv);
        parent.addView(layout2);

//children of layout2 LinearLayout

        TextView tv1 = new TextView(Linear.this);
        TextView tv2 = new TextView(Linear.this);
        TextView tv3 = new TextView(Linear.this);
        TextView tv4 = new TextView(Linear.this);

        layout2.addView(tv1);
        layout2.addView(tv2);
        layout2.addView(tv3);
        layout2.addView(tv4);

        tv1.setText("eyiwucwec");
        tv1.setTextColor(Color.RED); */
    }
}
