package com.example.popGame;

import android.graphics.Paint;
import android.graphics.RectF;

public class Square {
    float x,y,w,h;
    RectF square;
    Paint paint;
    int color,speed;

    public Square(float x,float y, float h,float w,int color,int speed){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.color = color;
        this.square = new RectF(x, y, x+w,y+h);
        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(color);
        this.speed=speed;
    }

    public void reCompute(){
        this.y-= this.speed;
        this.square = new RectF(this.x, this.y, this.x+this.w,this.y+this.h);
    }
}
