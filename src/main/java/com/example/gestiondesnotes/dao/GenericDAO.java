package com.example.gestiondesnotes.dao;

public interface GenericDAO<T> {
    void add(T entity);

    void update(T entity);

    void delete(int id);
}
