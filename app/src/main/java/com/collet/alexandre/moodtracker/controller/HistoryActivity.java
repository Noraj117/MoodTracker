package com.collet.alexandre.moodtracker.controller;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.collet.alexandre.moodtracker.R;
import com.collet.alexandre.moodtracker.model.MoodList;
import com.collet.alexandre.moodtracker.view.MoodAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private MoodList mood;
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

        ListView mListView = findViewById(R.id.listview);
        ArrayList<MoodList> moodList = new ArrayList<>();

        String sDate;
        LocalDate date;
        moodList = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            date = LocalDate.now(ZoneId.of("Europe/Paris")).minusDays(i);
            sDate = date.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
            String myGson = settings.getString(sDate, null);
            if (myGson != null) {
                mood = new MoodList();
                Type type = new TypeToken<MoodList>() {
                }.getType();
                mood = gson.fromJson(myGson, type);
                moodList.add(new MoodList(mood.getColor(), mood.getComment(), mood.getDate(1)));
            }
        }
        lAdapter = new MoodAdapter(this, moodList);
        mListView.setAdapter((ListAdapter) lAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}