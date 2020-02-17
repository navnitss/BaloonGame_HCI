package com.example.popGame;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
//Class to handle Input operations for the new HighScores
public class    FileIO {

    private static final String FILE_NAME = "players.txt";
    private static ArrayList<Person> persons = new ArrayList<Person>();

    public static void saveToFile(ArrayList<Person> persons, Context ctx) {
        FileOutputStream writeToFile = null;
        try {
            // File is opened in current Context
            writeToFile = ctx.openFileOutput(FILE_NAME, MODE_PRIVATE);
            for (Person player : persons) {
                String line = player.getName() + '\t' + player.getScore() + '\t' + player.getSdate() + '\n';
                writeToFile.write(line.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writeToFile != null) {
                try {
                    writeToFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // An array list containing all the players attributes
    //is returned after reading from the file
    public static ArrayList<Person> readFromFile(Context ctx) {
        FileInputStream fis = null;
        try {
            fis = ctx.openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] playerDetails;
                playerDetails = line.split("\t");
                Person player = new Person(playerDetails[0], playerDetails[1], playerDetails[2]);
                persons.add(player);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return persons;
    }
}
