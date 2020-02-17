package com.example.popGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.highscoretracker.R;

import static com.example.popGame.GameCanvas.score;

//Class to handle HighScore Entry
public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
//Functionality Implementation for the buttons
        Button button=findViewById(R.id.buttonadd);
        final Intent intent = new Intent(Activity2.this,Main2Activity.class);
        Intent intentSc = getIntent();
        Bundle bundle = intentSc.getExtras();
        EditText scora= findViewById(R.id.editText4);
        //String recvScore=Integer.toString(score);;
        String recvScore=bundle.getString("cscore");
        scora.setText(recvScore);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText naama= findViewById(R.id.editText3);
                EditText scora= findViewById(R.id.editText4);
                String strUserName = naama.getText().toString();
                //Setting and checking of the various constraints on the fields
                if(strUserName.isEmpty()) {
                    naama.setError("Name field cannot be empty");
                    return;
                }

                String strUserScore = scora.getText().toString();

                if(strUserScore.isEmpty()) {
                    scora.setError("Score field cannot be empty");
                    return;
                }
                String a= naama.getText().toString();
                String b=scora.getText().toString();
                if(a!=null&&b!=null) {
                    intent.putExtra("name", a);
                    intent.putExtra("score", b);
                    intent.putExtra("flag", "1");
                }
                startActivity(intent);

            }
        });
    }

}
