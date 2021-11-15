package com.service;

public interface CRUDService<T> {
    void save(T t);
    void update(T t);
    T findById(int id);
    void deleteById(int id);
}
