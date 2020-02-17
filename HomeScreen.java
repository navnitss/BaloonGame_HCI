package com.example.popGame;

import android.content.Intent;
import android.os.Bundle;

import com.example.highscoretracker.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public void viewHighScre(View view) {
        Intent intent = new Intent(getBaseContext(), Main2Activity.class);
        //intent.putExtra("VIEW_ONLY", true);
        startActivity(intent) ;
    }

    public void startGame(View view) {
        Intent intent = new Intent(getBaseContext(), GameScreen.class);
        startActivity(intent);
    }
}
