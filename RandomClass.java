package com.example.popGame;

import android.graphics.Color;
import android.graphics.RectF;

import java.util.Random;
//Random Class takes care of generating all the random values be it the coordinates or the sizes or the locations in the game, even the speed of the objects
public class RandomClass {
    Random random;
    int[] colors;
    int[][] ovals;
    int[][] squares;
    int[][] startPoints;
    int[] speed = new int[]{3,4,5};
    int[] shiftpos;

    public int[] getShiftpos() {
        return shiftpos;
    }

    public RandomClass(){
        this.random = new Random();
        this.colors = new int[]{Color.RED,Color.YELLOW,Color.RED, Color.GREEN,Color.RED, Color.BLUE, Color.WHITE,Color.RED};
        this.ovals = new int[][]{{60,100},{50,110},{90,139}, {100,166},{50,90},{80,60},{131,110},{142,160},{130,160},{110,170}};
        this.squares = new int[][]{{100,100},{120,120},{130,130}, {140,140},{150,150},{80,80},{90,90},{70,70},{60,60},{110,110}};
        this.startPoints = new int[][]{{100,1700},{200,1700},{300,1700},{400,1700},{500,1700},{600,1700},{700,1700},{800,1700}};
        this.shiftpos=new int[]{150,250,350,450,650,760,850,950,1050,1150,1250};
    }

    public int getRandomSpeed(){
        return this.speed[this.random.nextInt(this.speed.length)];
    }

    public int getShifpos() {
        return this.shiftpos[this.random.nextInt(this.shiftpos.length)];
    }

    public int getRandomColor(){
        return this.colors[this.random.nextInt(this.colors.length)];
    }

    public int randomNumber(){
        return this.random.nextInt();
    }

    public int[] getRandomOval(float x,float y){
        return this.ovals[this.random.nextInt(this.ovals.length)];

    }

    public int[] getRandomSquare(float x,float y){
        return this.squares[this.random.nextInt(this.ovals.length)];
    }

    public int randomColumn(){
        return this.random.nextInt(this.startPoints.length);
    }
}
