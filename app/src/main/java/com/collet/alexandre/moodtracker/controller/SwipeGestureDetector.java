package com.collet.alexandre.moodtracker.controller;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.collet.alexandre.moodtracker.R;

public class SwipeGestureDetector extends GestureDetector {

    private final static int DELTA_MIN = 50;
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

    public SwipeGestureDetector(final MainActivity context) {
        super(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("DEBUG", e1 + " - " + e2);

                float deltaX = e2.getX() - e1.getX();
                float deltaY = e2.getY() - e1.getY();

                if ( Math.abs( deltaX ) > Math.abs(deltaY)) {
                    if ( Math.abs( deltaX ) >= DELTA_MIN ) {
                        if (deltaX < 0) {
                            context.onSwipe( SwipeDirection.RIGHT_TO_LEFT );
                            return false;
                        } else {
                            context.onSwipe( SwipeDirection.LEFT_TO_RIGHT );
                            return false;
                        }
                    }
                } else {
                    if ( Math.abs(  deltaY ) >= DELTA_MIN ) {
                        if (deltaY < 0) {
                            context.onSwipe(SwipeDirection.BOTTOM_TO_TOP );
                            return true;
                        } else {
                            context.onSwipe(SwipeDirection.TOP_TO_BOTTOM );
                            return true;
                        }
                    }
                }
                return false;
            }


        });
    }

    public static enum SwipeDirection {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT, TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }
}
