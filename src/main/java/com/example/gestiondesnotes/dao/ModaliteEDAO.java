package com.example.gestiondesnotes.dao;

import com.example.gestiondesnotes.model.modalite_e;
import java.util.List;

public interface ModaliteEDAO extends GenericDAO<modalite_e> {

    modalite_e findModaliteEById(int id);
    List<modalite_e> findAllModaliteEs();
    List<modalite_e> findModaliteEByCodeElement(int codeElement);

}
