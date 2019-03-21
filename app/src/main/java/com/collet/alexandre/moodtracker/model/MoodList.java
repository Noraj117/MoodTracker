package com.collet.alexandre.moodtracker.model;

public class MoodList {
    private static int Color;
    private static String Comment;

    public MoodList(int color, String comment) {
        Color = color;
        Comment = comment;

    }

    public MoodList() {
    }

    public static int getColor() {
        return Color;
    }

    public static void setColor(int color) {
        Color = color;
    }

    public static String getComment() {
        return Comment;
    }

    public static void setComment(String comment) {
        Comment = comment;
    }


}
