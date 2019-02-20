package com.collet.alexandre.moodtracker.controller;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.collet.alexandre.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton mCommentBtn;
    private ImageButton mHistoryBtn;
    private ImageView mImageView;

    private SwipeGestureDetector mGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGestureDetector = new SwipeGestureDetector(this);

        mCommentBtn = (ImageButton) findViewById(R.id.main_activity_comment);
        mHistoryBtn = (ImageButton) findViewById(R.id.main_activity_history);

        mImageView = (ImageView) findViewById(R.id.main_activity_img);
        mImageView.setImageResource(R.drawable.smiley_happy);

        mCommentBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addComment();
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mGestureDetector.onTouchEvent(ev);
    }

    public void onSwipe(SwipeGestureDetector.SwipeDirection direction) {
        String message = "";
        switch(direction) {
            case LEFT_TO_RIGHT:
                message = "Left to right swipe";
                break;
            case RIGHT_TO_LEFT:
                message = "Right to left swipe";
                break;
            case TOP_TO_BOTTOM:
                message = "Top to bottom swipe";
                break;
            case BOTTOM_TO_TOP:
                message = "Bottom to top swipe";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void addComment() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText edittext = new EditText(this);

        builder.setMessage("Commentaire")
                .setView(edittext)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
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

}