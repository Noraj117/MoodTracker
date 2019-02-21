package com.collet.alexandre.moodtracker.controller;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.collet.alexandre.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton mCommentBtn;
    private ImageButton mHistoryBtn;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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