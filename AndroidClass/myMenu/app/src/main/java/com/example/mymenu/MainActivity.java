package com.example.mymenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button re,bl;
    LinearLayout sp;
    View.OnClickListener cl;
    int color = Color.GREEN;
    int width = 1;
    int type = 1;
    int style = 1;



    class herView extends View {

        Paint p;
        float x1, y1, x2, y2;

        herView(Context c){
            super(c);
            p = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            p.setColor(color);
            p.setStrokeWidth(width);
            if ( style == 1 ) {
                p.setStyle(Paint.Style.FILL);

            } else if ( style == 2 ) {
                p.setStyle(Paint.Style.STROKE);
            }

            if (type == 1 ){
                canvas.drawLine(x1, y1, x2, y2, p);
            } else if (type == 2) {
                canvas.drawRect(x1, y1, x2, y2, p);
            }

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch ( event.getAction() ){
                case MotionEvent.ACTION_DOWN :
                    x1 = event.getX();
                    y1 = event.getY();
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_MOVE:
                    x2 = event.getX();
                    y2 = event.getY();
                    invalidate();
                    break;
            }
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu sm, sm1, sm2, sm3, ssm1, sm4;


        sm1 = menu.addSubMenu("??????");
        sm1.add(0, 201, 0, "1px");
        sm1.add(0, 202, 0, "3px");
        sm1.add(0, 203, 0, "5px");
        sm1.add(0, 204, 0, "7px");
        sm1.add(0, 205, 0, "9px");

        sm = menu.addSubMenu("??????");
        sm.add(0, 101, 0, "??????");
        sm.add(0, 102, 0, "??????");
        sm.add(0, 103, 0, "??????");
        sm.add(0, 104, 0, "??????");
        ssm1 = sm.addSubMenu("??????");
        ssm1.add(0, 1011, 0, "?????????");
        ssm1.add(0, 1012, 0, "?????????");
        ssm1.add(0, 1013, 0, "??????");

        sm4 = menu.addSubMenu("??????");
        sm2 = sm4.addSubMenu("??????");
        sm2.add(0, 301, 0, "??????");
        sm2.add(0, 302, 0, "?????????");

        sm = sm4.addSubMenu("?????????");
        sm.add(0, 401, 0, "?????????");
        sm.add(0, 402, 0, "?????????");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId() ){
            case 101 :
                color = Color.YELLOW;
                break;

            case 102:
                color = Color.MAGENTA;
                break;

            case 103:
                color = Color.BLACK;
                break;

            case 104:
                color = Color.CYAN;
                break;

            case 1011:
                color = Color.DKGRAY;
                break;

            case 1012:
                color = Color.LTGRAY;
                break;

            case 1013:
                color = Color.GRAY;
                break;

            case 201:
                width = 1;
                break;

            case 202:
                width = 3;
                break;

            case 203:
                width = 5;
                break;

            case 204:
                width = 7;
                break;

            case 205:
                width = 9;
                break;

            case 301:
                type = 1;
                break;

            case 302:
                type = 2;
                break;

            case 401:
                style = 1;
                break;

            case 402:
                style = 2;
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        herView hv = new herView(this);

        setContentView(R.layout.activity_main);


        re = (Button) findViewById(R.id.red);
        bl = (Button) findViewById(R.id.blue);
        sp = (LinearLayout) findViewById(R.id.space);
        sp.addView(hv);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            switch(view.getId()){
                case R.id.red:
                    color = Color.RED;
                    break;

                case R.id.blue:
                    color = Color.BLUE;
                    break;

            }
            }
        };

        re.setOnClickListener(cl);
        bl.setOnClickListener(cl);
    }
}