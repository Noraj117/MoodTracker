package com.collet.alexandre.moodtracker.model;

public class MoodDataStorage {

    public MoodDataStorage() {

    }

    public MoodDataStorage(int couleur, String commentaire) {
        this.couleur = couleur;
        this.commentaire = commentaire;
    }

    private int couleur;
    private String commentaire;

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


}