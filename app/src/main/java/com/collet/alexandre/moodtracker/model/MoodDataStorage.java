package com.collet.alexandre.moodtracker.model;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MoodDataStorage {

    private static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private static final String MOOD_DATA = "MOOD_DATA";

    public static ArrayList<MoodList> mMoodList = new ArrayList<>();

    public MoodDataStorage(int color, String comment) {
        super();
    }


    public static void saveData(Context activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mMoodList);
        editor.putString(MOOD_DATA, json);
        editor.apply();
    }

    public static void loadData(Context activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(MOOD_DATA, null);
        Type type = new TypeToken<ArrayList<MoodList>>() {
        }.getType();
        mMoodList = gson.fromJson(json, type);

        if (mMoodList == null) {
            mMoodList = new ArrayList<>();
        }
    }

    public static void clearData(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
