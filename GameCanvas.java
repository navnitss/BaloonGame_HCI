package com.example.popGame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//Class that draws all the objects ie the circles and the Squares on the Screen
public class GameCanvas extends View {

    List<Oval> ovalList;
    Skeep s ;
    Intent intent= new Intent();
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static int score=0;
    public static int popcount=0;
    public static int highscore=0;
    List<Square> squareList;
    float x=-1,y=-1;
    RandomClass randClass;
    GameScreen gs;
    int counter;
    HashSet<Integer> cols = new HashSet<Integer>();


    public GameCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.x=event.getX();
        this.y=event.getY();
        int pos=0;
        int loc=0;
        int locs=0;
        int poss=0;
        int flag=0;
        for(Oval o: ovalList)
        {
            if(o.oval.contains(x,y)) {
                loc = pos;
                flag=1;
                break;
            }
            pos++;
        }
        for(Square s: squareList)
        {
            if(s.square.contains(x,y)) {
                locs = poss;
                flag=2;
                break;
            }
            poss++;
        }
        if(flag==1) {
            if (ovalList.get(loc).color==Color.RED) {
                score = score + 1;
                popcount=popcount+1;
            }
            else
                score=score-1;

            intent.putExtra("CurrScore",score);
            ovalList.remove(loc);
                repoplist();
                flag=0;
            //((Skeep) this.getApplication()).setCscore();
        }

        if(flag==2) {
            score=score-1;
            squareList.remove(locs);
            repoplists();
            flag=0;
            //((Skeep) this.getApplication()).setCscore();
        }

        invalidate();
        return true;
    }

    public void init(){
        this.randClass = new RandomClass();
        this.ovalList =  new ArrayList<Oval>();
        this.squareList = new ArrayList<Square>();
        startGame();
        animate(1000000000);
        this.counter=0;
    }
    public  void  repoplist()
    {
        int ind = this.randClass.randomColumn();
        int[] xy = this.randClass.startPoints[ind];
        System.out.println(xy[0]+" "+xy[1]);
        int[] wh = this.randClass.getRandomOval(xy[0],xy[1]);
        int color = this.randClass.getRandomColor();
        int speed = this.randClass.getRandomSpeed();
        Oval ov = new Oval(xy[0],xy[1],wh[0],wh[1],color,speed);
        this.ovalList.add(ov);


    }
    public  void  repoplists()
    {
        int ind = this.randClass.randomColumn();
        int[] xy = this.randClass.startPoints[ind];
        System.out.println(xy[0]+" "+xy[1]);
        int[] wh = this.randClass.getRandomSquare(xy[0],xy[1]);
        int color = this.randClass.getRandomColor();
        int speed = this.randClass.getRandomSpeed();
        Square ov = new Square(xy[0],xy[1],wh[0],wh[1],color,speed);
        this.squareList.add(ov);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(Oval o : ovalList){
            if(o.y<0)
            {
                o.y=o.y+1700;
                o.color=randClass.getRandomColor();
                o.speed=randClass.getRandomSpeed();
                o.x=randClass.getShifpos();
            }
            canvas.drawOval(o.oval, o.paint);
        }
        for(Square s : squareList){
            canvas.drawRect(s.square, s.paint);
            if(s.y<0)
            {
                s.y=s.y+1700;
                s.color=randClass.getRandomColor();
                s.speed=randClass.getRandomSpeed();
                s.x=randClass.getShifpos();
            }
        }
        interChecker();
    }

    public void interChecker()
    {
        for(Oval o : ovalList){

            for(Square s : squareList){
                if(RectF.intersects(o.oval, s.square))
                {
                    //System.out.println("INTERSECTION DETECTED");
                    s.square.offset(1,500);
                }
            }
        }
    }

    public void startGame(){
        int start_num = this.randClass.random.nextInt(12-6) + 6;
        //System.out.println("herer for "+start_num);

        for(int i=0;i<start_num;i++){
            if(this.randClass.randomNumber()%2==0){
                int ind = this.randClass.randomColumn();
//                while(cols.contains(ind)){
//                    System.out.println("asd");
//                    ind = this.randClass.randomColumn();
//                }
                cols.add(ind);
                int[] xy = this.randClass.startPoints[ind];
                System.out.println(xy[0]+" "+xy[1]);
                int[] wh = this.randClass.getRandomOval(xy[0],xy[1]);
                int color = this.randClass.getRandomColor();
                int speed = this.randClass.getRandomSpeed();
                Oval ov = new Oval(xy[0],xy[1],wh[0],wh[1],color,speed);
                this.ovalList.add(ov);
            }else{
                int ind = this.randClass.randomColumn();
//                while(cols.contains(ind)){
//                    ind = this.randClass.randomColumn();
//                }
                cols.add(ind);
                int[] xy = this.randClass.startPoints[ind];
                int[] wh = this.randClass.getRandomSquare(xy[0],xy[1]);
                int color = this.randClass.getRandomColor();
                int speed = this.randClass.getRandomSpeed();
                Square sq = new Square(xy[0],xy[1],wh[1],wh[0],color,speed);
                this.squareList.add(sq);
            }
        }
    }

    public void animate(long timerStart){
        new CountDownTimer(timerStart, 40) {

            public void onTick(long millisUntilFinished) {

                for(Oval o : ovalList){
                    o.reCompute();
                }
                for(Square s : squareList){
                    s.reCompute();
                }

                invalidate();
            }

            public void onFinish() {
                System.out.println("done");

            }
        }.start();

    }
}
