package com.example.gestiondesnotes.model;

import java.util.List;

public class Etudiant {
    private int code_etudiant;
    private String nom_etudiant;
    private String prenom_etudiant;
    private int code_filiere;
    private List<Note> notes;


    public Etudiant() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Etudiant(int code_etudiant, String nom_etudiant, String prenom_etudiant, int code_filiere, List<Note> notes) {
        this.code_etudiant = code_etudiant;
        this.nom_etudiant = nom_etudiant;
        this.prenom_etudiant = prenom_etudiant;
        this.code_filiere = code_filiere;
        this.notes = notes;
    }

    public int getCode_etudiant() {
        return code_etudiant;
    }

    public void setCode_etudiant(int code_etudiant) {
        this.code_etudiant = code_etudiant;
    }

    public String getNom_etudiant() {
        return nom_etudiant;
    }

    public void setNom_etudiant(String nom_etudiant) {
        this.nom_etudiant = nom_etudiant;
    }

    public String getPrenom_etudiant() {
        return prenom_etudiant;
    }

    public void setPrenom_etudiant(String prenom_etudiant) {
        this.prenom_etudiant = prenom_etudiant;
    }

    public int getCode_filiere() {
        return code_filiere;
    }

    public void setCode_filiere(int code_filiere) {
        this.code_filiere = code_filiere;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}