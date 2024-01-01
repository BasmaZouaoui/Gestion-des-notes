package com.example.gestiondesnotes.model;

public class Note {
    private int note_id;
    private double cc;
    private double tp;
    private double projet;
    private double presentations;
    private boolean absence_tp;
    private boolean absence_cc;
    private boolean absence_projet;
    private boolean absence_presentations;
    private int code_etudiant;
    private int code_element;
    private boolean valide;
    public Note() {
        super();
    }

    public Note(int note_id, double cc, double tp, double projet, double presentations, boolean absence_tp, boolean absence_cc, boolean absence_projet, boolean absence_presentations, int code_etudiant, int code_element, boolean valide) {
        this.note_id = note_id;
        this.cc = cc;
        this.tp = tp;
        this.projet = projet;
        this.presentations = presentations;
        this.absence_tp = absence_tp;
        this.absence_cc = absence_cc;
        this.absence_projet = absence_projet;
        this.absence_presentations = absence_presentations;
        this.code_etudiant = code_etudiant;
        this.code_element = code_element;
        this.valide = valide;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public double getCc() {
        return cc;
    }

    public void setCc(double cc) {
        this.cc = cc;
    }

    public double getTp() {
        return tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    public double getProjet() {
        return projet;
    }

    public void setProjet(double projet) {
        this.projet = projet;
    }

    public double getPresentations() {
        return presentations;
    }

    public void setPresentations(double presentations) {
        this.presentations = presentations;
    }

    public boolean isAbsence_tp() {
        return absence_tp;
    }

    public void setAbsence_tp(boolean absence_tp) {
        this.absence_tp = absence_tp;
    }

    public boolean isAbsence_cc() {
        return absence_cc;
    }

    public void setAbsence_cc(boolean absence_cc) {
        this.absence_cc = absence_cc;
    }

    public boolean isAbsence_projet() {
        return absence_projet;
    }

    public void setAbsence_projet(boolean absence_projet) {
        this.absence_projet = absence_projet;
    }

    public boolean isAbsence_presentations() {
        return absence_presentations;
    }

    public void setAbsence_presentations(boolean absence_presentations) {
        this.absence_presentations = absence_presentations;
    }

    public int getCode_etudiant() {
        return code_etudiant;
    }

    public void setCode_etudiant(int code_etudiant) {
        this.code_etudiant = code_etudiant;
    }

    public int getCode_element() {
        return code_element;
    }

    public void setCode_element(int code_element) {
        this.code_element = code_element;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
}