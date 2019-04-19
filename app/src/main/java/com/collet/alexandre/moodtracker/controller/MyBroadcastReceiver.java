package com.collet.alexandre.moodtracker.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.collet.alexandre.moodtracker.model.MoodList;

import java.util.ArrayList;

class MyBroadcastReceiver extends BroadcastReceiver {
    private MoodList mood;
    ArrayList<MoodList> moodList = new ArrayList<>();


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MyTestService.class);
        i.putExtra("foo", "bar");
        moodList.add(new MoodList(mood.getColor(), mood.getComment(), mood.getDate(1)));
        context.startService(i);
    }
}