package com.example.gestiondesnotes.model;

public class modalite_e {

    private int id_modalite;
    private float p_cc;
    private float p_tp;
    private float p_projet;
    private float p_presentation;
    private int code_element;

    public modalite_e() {
    }

    public modalite_e(int id_modalite, float p_cc, float p_tp, float p_projet, float p_presentation, int code_element) {
        this.id_modalite = id_modalite;
        this.p_cc = p_cc;
        this.p_tp = p_tp;
        this.p_projet = p_projet;
        this.p_presentation = p_presentation;
        this.code_element = code_element;
    }

    public int getId_modalite() {
        return id_modalite;
    }

    public float getP_cc() {
        return p_cc;
    }

    public float getP_tp() {
        return p_tp;
    }

    public float getP_projet() {
        return p_projet;
    }

    public float getP_presentation() {
        return p_presentation;
    }

    public int getCode_element() {
        return code_element;
    }

    public void setId_modalite(int id_modalite) {
        this.id_modalite = id_modalite;
    }

    public void setP_cc(float p_cc) {
        this.p_cc = p_cc;
    }

    public void setP_tp(float p_tp) {
        this.p_tp = p_tp;
    }

    public void setP_projet(float p_projet) {
        this.p_projet = p_projet;
    }

    public void setP_presentation(float p_presentation) {
        this.p_presentation = p_presentation;
    }

    public void setCode_element(int code_element) {
        this.code_element = code_element;
    }
}
