package com.example.gestiondesnotes.model;

public class module {
    private int code_module;
    private String nom_module;
    private String semestre;
    private int code_filiere;

    public module() {
        super();
    }

    public module(int code_module, String nom_module, String semestre, int code_filiere) {
        super();
        this.code_module = code_module;
        this.nom_module = nom_module;
        this.semestre = semestre;
        this.code_filiere = code_filiere;
    }

    public int getCode_module() {
        return code_module;
    }

    public void setCode_module(int code_module) {
        this.code_module = code_module;
    }

    public String getNom_module() {
        return nom_module;
    }

    public void setNom_module(String nom_module) {
        this.nom_module = nom_module;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getCode_filiere() {
        return code_filiere;
    }

    public void setCode_filiere(int code_filiere) {
        this.code_filiere = code_filiere;
    }


}