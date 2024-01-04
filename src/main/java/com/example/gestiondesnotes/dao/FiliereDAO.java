package com.example.gestiondesnotes.dao;

import java.util.List;

import com.example.gestiondesnotes.model.Filiere;

public interface FiliereDAO extends GenericDAO<Filiere> {

    List<Filiere> getAll();

    Filiere getById(int code_filiere);

}
