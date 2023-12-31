package com.example.gestiondesnotes.dao;

import java.util.List;

import com.example.gestiondesnotes.model.Element;

public interface ElementDAO extends GenericDAO<Element> {

    List<Element> getElementModulesByModule(String code_module);

    Element getById(int code_element);

    List<Element> getByProf(int code_prof);

}
