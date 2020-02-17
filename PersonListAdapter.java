package com.example.popGame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.highscoretracker.R;

import java.util.ArrayList;

//Adapter for the custom format List view display
public class PersonListAdapter extends ArrayAdapter<Person> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;


    public PersonListAdapter(Context context, int resource, ArrayList<Person> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name= getItem(position).getName();
        String score= getItem(position).getScore();
        String sdate= getItem(position).getSdate();
        Person person = new Person(name,score,sdate);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView= inflater.inflate(mResource,parent,false);
        TextView tvname= (TextView) convertView.findViewById(R.id.textView1);
        TextView tscore= (TextView) convertView.findViewById(R.id.textView2);
        TextView tvdate= (TextView) convertView.findViewById(R.id.textView3);

        tvname.setText(name);
        tscore.setText(score);
        tvdate.setText(sdate);

        return convertView;

    }


}
