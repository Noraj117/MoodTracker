package com.collet.alexandre.moodtracker.controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.collet.alexandre.moodtracker.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HistoryActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        SharedPreferences settings = getSharedPreferences("humeurFile", 0);
        Set<String> set = new HashSet<String>();
        set = settings.getStringSet("01/03/2019", null);

        List<String> List = new ArrayList<String>(set);


    }
}
