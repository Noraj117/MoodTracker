package com.collet.alexandre.moodtracker.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.collet.alexandre.moodtracker.R;
import com.collet.alexandre.moodtracker.model.MoodList;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final int[][] LIST_COLOR_IMG = {
            {R.color.faded_red,
                    R.color.warm_grey,
                    R.color.cornflower_blue_65,
                    R.color.light_sage,
                    R.color.banana_yellow},
            {R.drawable.smiley_sad,
                    R.drawable.smiley_disappointed,
                    R.drawable.smiley_normal,
                    R.drawable.smiley_happy,
                    R.drawable.smiley_super_happy}};
    private static final int SWIPE_MIN_DISTANCE = 150;
    public static int index = 3;
    private GestureDetectorCompat mDetector;
    private ImageButton mCommentBtn;
    private ImageButton mHistoryBtn;
    private ImageView mSmileyBtn;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCommentBtn = findViewById(R.id.main_activity_comment);
        mHistoryBtn = findViewById(R.id.main_activity_history);
        mSmileyBtn = findViewById(R.id.main_activity_img);
        mRelativeLayout = findViewById(R.id.main_activity_layout_background);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        mCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment();
            }
        });


        mHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivity = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivity);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    /*
    This method is for add comment and color of the actual mood, and save it in SharedPreferences with Gson library.
     */
    private void addComment() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        final EditText comment = new EditText(MainActivity.this);

        builder.setMessage(R.string.strCommentaire);
        builder.setView(comment);
        builder.setPositiveButton(R.string.strValider, new DialogInterface.OnClickListener() {
            /*
            Method for intercept click, and comment in the SharedPreferences, using Gson library.
             */
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                String myComment = comment.getText().toString();
                int color = ((ColorDrawable) mRelativeLayout.getBackground()).getColor();

                MoodList mood = new MoodList();
                mood.setComment(myComment);
                mood.setColor(color);

                Gson gson = new Gson();

                String myGson = gson.toJson(mood);

                Date date = new Date();
                String myFormatedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
               /* String myFormatedDate = "07/04/2019";*/



                SharedPreferences sharedPreferences = getSharedPreferences("humeurFile", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(myFormatedDate, myGson);
                editor.apply();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create()
                .show();
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDown: " + event.toString());
            return true;
        }

        /*
        This method is set to swipe on the screen. It get the positions of event1 and event2.
         */
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());

            if (index < LIST_COLOR_IMG[0].length -1 && event1.getY() - event2.getY() > SWIPE_MIN_DISTANCE) {
                index++;
                mSmileyBtn.setImageResource(LIST_COLOR_IMG[1][index]);
                mRelativeLayout.setBackgroundColor(getResources().getColor(LIST_COLOR_IMG[0][index]));
                getSound(R.raw.yoshi_tongue);

            } else if (index > 0 && event2.getY() - event1.getY() > SWIPE_MIN_DISTANCE) {
                index--;
                mSmileyBtn.setImageResource(LIST_COLOR_IMG[1][index]);
                mRelativeLayout.setBackgroundColor(getResources().getColor(LIST_COLOR_IMG[0][index]));
                getSound(R.raw.doh_homer);

            } else
                return false;

            return true;
        }
    }
    /*
     * This method allow you to add sound from raw folder in app with MediaPlayer.
     */
    public void getSound(int sound){
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, sound);
        mediaPlayer.start();
    }

    private void AlarmMidnight(Context context) {

        AlarmManager alarmManager;
        PendingIntent pendingIntent;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, 1);

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }


}