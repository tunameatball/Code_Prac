package com.example.rectangle;

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

public class MainActivity extends AppCompatActivity {

    YourView yv;
    String s = "";
    float x1, y1, x2, y2;
    int color = Color.BLACK;
    int type = 1;
    Paint p;
    int width = 1;
    float r;

    class YourView extends View {


        YourView(Context c){
            super(c);
            p = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

//            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(width);
            p.setColor(color);
//            p.setStrokeWidth(10);

            canvas.drawText(s, 10, 40, p);
            if (type == 1){
                canvas.drawLine(x1,y1,x2,y2,p);
            }
            else if(type == 2){
                canvas.drawRect(x1,y1,x2,y2,p);
            } else if (type == 3) {
                canvas.drawOval(x1,y1,x2,y2,p);
            } else if(type == 4) {
                r = (float) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
                canvas.drawCircle(x1,y1, r, p);
            } else if(type == 5){
                canvas.drawLine((x1+x2)/2, y1, x1, y2,p);
                canvas.drawLine(x1,y2,x2,y2,p);
                canvas.drawLine((x1+x2)/2, y1, x2, y2, p);
            } else if (type == 6) {
                canvas.drawLine(x1,y1,x2,y1,p);
                canvas.drawLine(x1,y1, (x1+x2)/2, y2, p);
                canvas.drawLine(x2,y1,(x1+x2)/2, y2,p);
            }


        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch( event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    s = String.format("Down x = %f y = %f  ", event.getX(), event.getY());
                    x1 = event.getX();
                    y1 = event.getY();
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    s = String.format("Up x = %f y = %f  ", event.getX(), event.getY());
                    x2 = event.getX();
                    y2 = event.getY();
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:
                    s = String.format("Move x = %f y = %f  ", event.getX(), event.getY());
                    x2 = event.getX();
                    y2 = event.getY();
                    invalidate();
                    break;
            }
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        yv = new YourView(this);
        setContentView(yv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 101, 0, "??????");
        menu.add(0, 102, 0, "??????");
        menu.add(0, 103, 0, "??????");

        SubMenu sm;
        sm = menu.addSubMenu("?????? ??????");
        sm.add(0, 201, 0, "??????");
        sm.add(0, 202, 0, "?????????");
        sm.add(0, 203, 0, "??????");
        sm.add(0, 204,0, "???");
        sm.add(0, 205, 0, "?????????");
        sm.add(0,206,0,"????????????");

        sm = menu.addSubMenu("?????????");
        sm.add(0, 301, 0, "?????????");
        sm.add(0,302, 0, "?????????");

        sm = menu.addSubMenu("??? ??????");
        sm.add(0,401,0,"1px");
        sm.add(0,402,0,"3px");
        sm.add(0,403,0,"5px");
        sm.add(0,404,0,"7px");
        sm.add(0, 405, 0, "10px");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case 101:
                color = Color.RED;
                yv.invalidate();
                break;
            case 102:
                color = Color.BLUE;
                yv.invalidate();
                break;
            case 103:
                color = Color.GREEN;
                yv.invalidate();
                break;

            case 201:
                type = 1;
                yv.invalidate();
                break;

            case 202:
                type = 2;
                yv.invalidate();
                break;

            case 203:
                type = 3;
                yv.invalidate();
                break;

            case 204:
                type = 4;
                yv.invalidate();
                break;

            case 205:
                type = 5;
                yv.invalidate();
                break;

            case 206:
                type = 6;
                yv.invalidate();
                break;

            case 301:
                p.setStyle(Paint.Style.FILL);
                yv.invalidate();
                break;

            case 302:
                p.setStyle(Paint.Style.STROKE);
                yv.invalidate();
                break;

            case 401:
                width = 1;
                yv.invalidate();
                break;

            case 402:
                width = 3;
                yv.invalidate();
                break;

            case 403:
                width = 5;
                yv.invalidate();
                break;

            case 404:
                width = 7;
                yv.invalidate();
                break;

            case 405:
                width = 10;
                yv.invalidate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}