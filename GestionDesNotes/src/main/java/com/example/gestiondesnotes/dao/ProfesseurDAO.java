package com.example.gestiondesnotes.dao;

import java.util.List;

import com.example.gestiondesnotes.model.Professeur;

public interface ProfesseurDAO extends GenericDAO<Professeur> {

    List<Professeur> getAll();

    Professeur getById(int code_prof);

    Professeur getProfesseurByUsernameAndPassword(String username, String password);

}

