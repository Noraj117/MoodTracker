package com.collet.alexandre.moodtracker.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.collet.alexandre.moodtracker.R;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.collet.alexandre.moodtracker.model.MoodDataStorage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HistoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        SharedPreferences settings = getSharedPreferences("humeurFile", 0);
        MoodDataStorage mood;
        Gson gson = new Gson();

        for(int i=0; i<7; i++) {

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -i);
            SimpleDateFormat format1 = new SimpleDateFormat("dd/mm/yyyy");
            String maDateFormatee = format1.format(cal.getTime());
            String monGson = settings.getString(maDateFormatee, null);


            if (monGson != null) {
                mood = new MoodDataStorage();
                Type type = new TypeToken<MoodDataStorage>() {
                }.getType();
                mood = gson.fromJson(monGson, type);
                Toast.makeText(this, "test", Toast.LENGTH_LONG).show();

            }
        }
}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }
}
