package com.collet.alexandre.moodtracker.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.collet.alexandre.moodtracker.R;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;



import com.collet.alexandre.moodtracker.model.MoodDataStorage;
import com.collet.alexandre.moodtracker.model.MoodList;
import com.collet.alexandre.moodtracker.view.MoodAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HistoryActivity extends AppCompatActivity {


    private ListView mListView;
    private MoodAdapter lAdapter;
    public static final int[][] LIST_COLOR = {
            {R.color.faded_red,
                    R.color.warm_grey,
                    R.color.cornflower_blue_65,
                    R.color.light_sage,
                    R.color.banana_yellow}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        SharedPreferences settings = getSharedPreferences("humeurFile", 0);
        MoodDataStorage mood;
        Gson gson = new Gson();

        mListView = (ListView) findViewById(R.id.moodListXml);
        ArrayList<MoodDataStorage> moodList = new ArrayList<>();
        /*moodList.add(new MoodDataStorage(1,"Il y a une semaine"));
        moodList.add(new MoodDataStorage(2,"Il y a 6 jours"));
        moodList.add(new MoodDataStorage(3,"Il y a 5 jours"));
        moodList.add(new MoodDataStorage(4,"Il y a 4 jours"));
        moodList.add(new MoodDataStorage(5,"Il y a 3 jours"));
        moodList.add(new MoodDataStorage(6,"Avant-hier"));
        moodList.add(new MoodDataStorage(7,"Hier"));



        lAdapter = new MoodAdapter(this,mListView);*/
        mListView.setAdapter(lAdapter);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy ");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String[] days = new String[6];
        days[0] = sdf.format(date);

        for(int i = 1; i < 6; i++){
            cal.add(Calendar.DAY_OF_MONTH, -1);
            date = cal.getTime();
            days[i] = sdf.format(date);
        }
        String monGson = settings.getString(String.valueOf(date), null);

        if (monGson != null) {
            moodList = new ArrayList<>();
            Type type = new TypeToken<MoodDataStorage>() {
            }.getType();
            moodList = gson.fromJson(monGson, type);
            Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
            moodList.add(new MoodDataStorage(MoodList.getColor(), MoodList.getComment()));

        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView = (ListView) parent.getAdapter().getItem(position);
        Intent intent = new Intent(HistoryActivity.this, MoodDataStorage.class);
        String message = "";
        intent.putExtra(toString(), message);
        startActivity(intent);
    }
}
