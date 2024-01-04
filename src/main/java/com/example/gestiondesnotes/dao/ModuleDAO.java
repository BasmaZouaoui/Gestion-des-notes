package com.example.gestiondesnotes.dao;

import java.util.List;

import com.example.gestiondesnotes.model.module;

public interface ModuleDAO extends GenericDAO<module> {

    module getById(int code_module);

    List<module> getAllModules();
}
