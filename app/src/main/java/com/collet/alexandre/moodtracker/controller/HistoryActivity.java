package com.collet.alexandre.moodtracker.controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.collet.alexandre.moodtracker.R;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.collet.alexandre.moodtracker.model.MoodList;
import com.collet.alexandre.moodtracker.view.MoodAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HistoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


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
        MoodList mood;
        Gson gson = new Gson();

        mListView = (ListView) findViewById(R.id.moodListXml);
        ArrayList<MoodList> moodList = new ArrayList<>();

        String sDate;
        LocalDate date;

        for(int i = 0; i < 7; i++){
            date = LocalDate.now(ZoneId.of("Europe/Paris")).minusDays(i);
            sDate = date.format(DateTimeFormatter.ofPattern("d/MM/YYYY"));
            String monGson = settings.getString(sDate, null);
            if (monGson != null) {
                mood = new MoodList();
                moodList = new ArrayList<>();
                Type type = new TypeToken<MoodList>() {
                }.getType();
                mood = gson.fromJson(monGson, type);
                Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
                moodList.add(new MoodList(mood.getColor(), mood.getComment()));
            }
        }
        lAdapter = new MoodAdapter(this,moodList);
        mListView.setAdapter(lAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}