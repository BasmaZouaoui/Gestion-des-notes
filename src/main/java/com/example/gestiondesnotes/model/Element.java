package com.example.gestiondesnotes.model;

public class Element {
    private int code_element;
    private String nom_element;
    private double coefficient;
    private int code_module;
    private int code_prof;

    public Element() {
        super();
    }


    public Element(int code_element, String nom_element, double coefficient, int code_module, int code_prof) {
        super();
        this.code_element = code_element;
        this.nom_element = nom_element;
        this.coefficient = coefficient;
        this.code_module = code_module;
        this.code_prof = code_prof;
    }


    public int getCode_element() {
        return code_element;
    }


    public void setCode_element(int code_element) {
        this.code_element = code_element;
    }


    public String getNom_element() {
        return nom_element;
    }


    public void setNom_element(String nom_element) {
        this.nom_element = nom_element;
    }


    public double getCoefficient() {
        return coefficient;
    }


    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }


    public int getCode_module() {
        return code_module;
    }


    public void setCode_module(int code_module) {
        this.code_module = code_module;
    }


    public int getCode_prof() {
        return code_prof;
    }


    public void setCode_prof(int code_prof) {
        this.code_prof = code_prof;
    }

}