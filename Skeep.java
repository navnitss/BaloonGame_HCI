package com.example.popGame;

import android.app.Application;

public class Skeep extends Application {
    int cscore;
    public int getCscore() {
        System.out.println("Pulled Score ="+cscore);
        return this.cscore;
    }

    public void setCscore(int cscore) {
        this.cscore = cscore;
        System.out.println("Setscore Score ="+cscore);
    }


}
