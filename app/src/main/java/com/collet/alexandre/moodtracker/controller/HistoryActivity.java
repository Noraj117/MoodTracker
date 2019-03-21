package com.collet.alexandre.moodtracker.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import java.util.List;


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
    private List<MoodList> mMoodList = new ArrayList<MoodList>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        String sDate;
        LocalDate date;

        SharedPreferences settings = getSharedPreferences("humeurFile", 0);
        Gson gson = new Gson();

        mListView = (ListView) findViewById(R.id.moodListXml);
        ArrayList<MoodList> moodList = new ArrayList<>();
        mMoodList.add(new MoodList(1,"Il y a une semaine"));
        mMoodList.add(new MoodList(2,"Il y a 6 jours"));
        mMoodList.add(new MoodList(3,"Il y a 5 jours"));
        mMoodList.add(new MoodList(4,"Il y a 4 jours"));
        mMoodList.add(new MoodList(5,"Il y a 3 jours"));
        mMoodList.add(new MoodList(6,"Avant-hier"));
        mMoodList.add(new MoodList(7,"Hier"));

        lAdapter = new MoodAdapter(this, moodList);
        mListView.setAdapter(lAdapter);

        for(int i = 0; i < 7; i++){
            date = LocalDate.now(ZoneId.of("Europe/Paris")).minusDays(i);
            sDate = date.format(DateTimeFormatter.ofPattern("d/MM/YYYY"));
            String monGson = settings.getString(String.valueOf(sDate), null);
            if (monGson != null) {
                mMoodList = new ArrayList<>();
                Type type = new TypeToken<MoodList>() {
                }.getType();
                mMoodList = gson.fromJson(monGson, type);
                Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
                mMoodList.add(new MoodList(MoodList.getColor(), MoodList.getComment()));
            }
        }

}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView = (ListView) parent.getAdapter().getItem(position);
        Intent intent = new Intent(HistoryActivity.this, MoodList.class);
        String message = "";
        intent.putExtra(toString(), message);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
