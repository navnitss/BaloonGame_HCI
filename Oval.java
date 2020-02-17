package com.example.popGame;

import android.graphics.Paint;
import android.graphics.RectF;

public class Oval {
    RectF oval;
    Paint paint;
    float x,y,w,h;
    int color,speed;

    public Oval(float x,float y,float w,float h,int color,int speed){
        this.w=w;
        this.x=x;
        this.y=y;
        this.h=h;
        this.color=color;
        this.oval = new RectF(x, y, x+w,y+h);
        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(color);
        this.speed=speed;
    }

    public void reCompute(){
        this.y-=this.speed;
        this.oval = new RectF(this.x, this.y, this.x+this.w,this.y+this.h);
    }
}
