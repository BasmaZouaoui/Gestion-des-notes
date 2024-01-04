package com.example.gestiondesnotes.model;

public class Professeur {
    private int code_prof;
    private String nom_prof;
    private String prenom_prof;
    private String specialite;
    private String username;
    private String password;


    public Professeur() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Professeur(int code_prof, String nom_prof, String prenom_prof, String specialite, String username,
                      String password) {
        super();
        this.code_prof = code_prof;
        this.nom_prof = nom_prof;
        this.prenom_prof = prenom_prof;
        this.specialite = specialite;
        this.username = username;
        this.password = password;
    }


    public int getCode_prof() {
        return code_prof;
    }


    public void setCode_prof(int code_prof) {
        this.code_prof = code_prof;
    }


    public String getNom_prof() {
        return nom_prof;
    }


    public void setNom_prof(String nom_prof) {
        this.nom_prof = nom_prof;
    }


    public String getPrenom_prof() {
        return prenom_prof;
    }


    public void setPrenom_prof(String prenom_prof) {
        this.prenom_prof = prenom_prof;
    }


    public String getSpecialite() {
        return specialite;
    }


    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}