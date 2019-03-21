package com.collet.alexandre.moodtracker.model;

public class MoodList {
    private static int Color;
    private static String Comment;
    private static String Date;

    public MoodList(int color, String comment, String date) {
        Color = color;
        Comment = comment;
        Date = date;

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

    public static String getDate(int value) {
        String Days[] = {"Hier", "Avant_hier", "Il y a 3 jours", "Il y a 4 jours", "Il y a 5 jours", "Il y a 6 jours", "Il y a une semaine"};
        Date = Days[value];
        return Date;
    }

}
