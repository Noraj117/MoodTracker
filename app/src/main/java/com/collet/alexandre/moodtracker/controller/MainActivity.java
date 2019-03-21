package com.collet.alexandre.moodtracker.controller;

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
    private int index = 3;
    private GestureDetectorCompat mDetector;

    private ImageButton mCommentBtn;
    private ImageButton mHistoryBtn;
    private ImageView mSmileyBtn;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCommentBtn = (ImageButton) findViewById(R.id.main_activity_comment);
        mHistoryBtn = (ImageButton) findViewById(R.id.main_activity_history);
        mSmileyBtn = (ImageView) findViewById(R.id.main_activity_img);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.main_activity_layout_background);
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
    This method is for add a comment of the actual mood
     */
    private void addComment() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        final EditText commentaire = new EditText(MainActivity.this);

        builder.setMessage(R.string.strCommentaire);
        builder.setView(commentaire);
        builder.setPositiveButton(R.string.strValider, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                String monCommentaireSaisi = commentaire.getText().toString();
                int color = ((ColorDrawable) mRelativeLayout.getBackground()).getColor();

                MoodList mood = new MoodList();
                mood.setComment(monCommentaireSaisi);
                mood.setColor(color);

                Gson gson = new Gson();

                String monGson = gson.toJson(mood);

                Date date = new Date();
                String maDateFormatee = new SimpleDateFormat("dd/MM/yyyy").format(date);

                SharedPreferences sharedPreferences = getSharedPreferences("humeurFile", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(maDateFormatee, monGson);
                editor.commit();
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

    public void getSound(int sound){
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, sound);
        mediaPlayer.start();
    }
}