package com.example.popGame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.highscoretracker.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.CountDownTimer;
import android.widget.TextView;

import static com.example.popGame.GameCanvas.highscore;
import static com.example.popGame.GameCanvas.popcount;
import static com.example.popGame.GameCanvas.score;

//Class to handle the Timer Thread and the various Score and the Timer Countdown
public class  GameScreen extends AppCompatActivity {
    Context context;
    CountDownTimer mCountDownTimer;
    TextView timer;
   TextView scoreD;
   String f;
    int temp=0;
    int IncCount=0;
    int cdTime=60000;
    long currentTimeRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setAndStartTimer();
        //Skeep sk= (Skeep)getApplicationContext();
    }



    public void setAndStartTimer(){
        timer = findViewById(R.id.gameTimeTextView);
        scoreD=findViewById(R.id.gameTimeTextView2);
        //String s=score.toString();
        mCountDownTimer = new CountDownTimer(cdTime, 1000) {

            public void onTick(long millisUntilFinished) {
                cdTime=cdTime-1000;
                currentTimeRemaining = millisUntilFinished / 1000;
                long min = currentTimeRemaining/60;
                long sec = currentTimeRemaining%60;

                timer.setText(String.format("%02d", min)+":"+String.format("%02d", sec));
                //timer.setText(x);
                if(score>temp)
                {
                    temp=score;
                    IncCount=IncCount+1;
                    if(popcount==10) {
                        mCountDownTimer.cancel();

                        cdTime = cdTime + 10000;
                        popcount=0;
                        setAndStartTimer();
                    }
                }
                scoreD.setText(String.format("%03d", score));

                System.out.println("Display SCORING="+score);
            }

            public void onFinish() {
                timer.setText("done!");
                if(score>highscore) {
                    highscore =score;
                    String x = Integer.toString(score);
                    Intent intent = new Intent(getBaseContext(), Activity2.class);
                    intent.putExtra("cscore", x);
                    score=0;
                    startActivity(intent);
                }
                else
                {
                    Intent intentv = new Intent(getBaseContext(), HomeScreen.class);
                    startActivity(intentv);
                }
            }


        }.start();
    }

}
