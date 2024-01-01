package com.example.gestiondesnotes.model;

public class Filiere {
    private int code_filiere;
    private String nom_filiere;

    public Filiere() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Filiere(int code_filiere, String nom_filiere) {
        super();
        this.code_filiere = code_filiere;
        this.nom_filiere = nom_filiere;
    }

    public int getCode_filiere() {
        return code_filiere;
    }

    public void setCode_filiere(int code_filiere) {
        this.code_filiere = code_filiere;
    }

    public String getNom_filiere() {
        return nom_filiere;
    }

    public void setNom_filiere(String nom_filiere) {
        this.nom_filiere = nom_filiere;
    }


}