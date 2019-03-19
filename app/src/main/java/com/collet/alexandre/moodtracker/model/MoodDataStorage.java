package com.collet.alexandre.moodtracker.model;

public class MoodDataStorage {

    private int Couleur;
    private String Commentaire;

    public MoodDataStorage(int couleur, String commentaire ) {
        this.Couleur = couleur;
        this.Commentaire = commentaire;
    }

    public MoodDataStorage() {

    }

    public int getCouleur() {
        return Couleur;
    }

    public void setCouleur(int couleur) {
        this.Couleur = couleur;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.Commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "MoodDataStorage{" +
                "Couleur=" + Couleur +
                ", Commentaire='" + Commentaire + '\'' +
                '}';
    }
}