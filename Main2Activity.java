package com.example.popGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.ListView;

import com.example.highscoretracker.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    int count = 0;
    private final String TAG = "MainActivity";
    static ArrayList<Person> peopleList = new ArrayList<>();
    //Initialization of the listview data o create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView);
        //Accept data and add into list via Intent
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String score = i.getStringExtra("score");
        Date currentTime = Calendar.getInstance().getTime();
        String c = currentTime.toString();

        //FileIO.java is called to perform the read operation
        if (peopleList.isEmpty()) {
            peopleList = FileIO.readFromFile(this);
            peopleList.sort(new IdSorter());
        }
        if (name != null) {
            Person obj = new Person(name, score, c);
            peopleList.add(obj);
        }
        count = count + 1;
        peopleList.sort(new IdSorter());
        PersonListAdapter adapter = new PersonListAdapter(this, R.layout.adapter_view_layout, peopleList);
        mListView.setAdapter(adapter);
        ////FileIO.java is used to save the final data into the text file
        FileIO.saveToFile(peopleList, this);

    }

    //Implemets the sorting function for the list
    public class IdSorter implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o2.getScore().compareTo(o1.getScore());
        }
    }

    //opens the sencond activity via intenct
    void openActivity2() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}

