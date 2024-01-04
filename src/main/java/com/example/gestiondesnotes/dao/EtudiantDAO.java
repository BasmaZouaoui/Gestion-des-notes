package com.example.gestiondesnotes.dao;

import java.util.List;

import com.example.gestiondesnotes.model.Etudiant;

public interface EtudiantDAO extends GenericDAO<Etudiant> {

    List<Etudiant> getAll();
    List<Etudiant> getEtudiantsByElement(int codeElement);
    Etudiant getById(int code_etudiant);

}
