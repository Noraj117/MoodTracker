package com.collet.alexandre.moodtracker.model;


/*
Base methods for view and adapter.
We store and save values from comment, color and date.
 */

public class MoodList {
    private int Color;
    private String Comment;
    private String Date;

    public MoodList(int color, String comment, String date) {
        Color = color;
        Comment = comment;
        Date = date;

    }

    public MoodList() {
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public  String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getDate(int value) {
        String Days[] = {"Hier", "Avant-hier", "Il y a 3 jours", "Il y a 4 jours", "Il y a 5 jours", "Il y a 6 jours", "Il y a 7 jours"};
        Date = Days[value];
        return Date;
    }

}
