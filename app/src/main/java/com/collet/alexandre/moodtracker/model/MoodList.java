package com.collet.alexandre.moodtracker.model;

public class MoodList {
    private static int mColor;
    private int mSmileyValue;
    private static String mComment;
    private int mIconComment;
    private String mDate;

    public MoodList(int color, int smileyValue, String comment, int iconComment, String date) {
        mColor = color;
        mSmileyValue = smileyValue;
        mComment = comment;
        mIconComment = iconComment;
        mDate = date;
    }

    public MoodList() {

    }

    public static int getColor() {
        return mColor;
    }

    public int getSmileyValue() {
        return mSmileyValue;
    }

    public static String getComment() {
        return mComment;
    }

    public int getIconComment() {
        return mIconComment;
    }

    public String getDate() {
        return mDate;
    }

    public static void setmColor(int mColor) {
        MoodList.mColor = mColor;
    }

    public void setSmileyValue(int smileyValue) {
        mSmileyValue = smileyValue;
    }

    public static void setmComment(String mComment) {
        MoodList.mComment = mComment;
    }

    public void setIconComment(int iconComment) {
        mIconComment = iconComment;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getDate(int value) {
        String dayTab[] = {"Hier", "Avant-hier",
                "trois jours", "quatre jours", "cinq jours", "six jours", "une semaine"};
        mDate = dayTab[value];

        if (value >= 2)
            return "Il y a " + mDate;

        return mDate;
    }

}
