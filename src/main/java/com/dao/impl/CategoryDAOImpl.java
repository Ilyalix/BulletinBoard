package com.dao.impl;

import com.dao.CrudDAO;
import com.domain.Category;
import com.domain.Category_;
import com.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

@Repository
@Transactional
public class CategoryDAOImpl implements CrudDAO<Category> {

    @Autowired
    CategoryRepository repository;

    @Override
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    public void update(Category category) {
        repository.save(category);
    }

    @Override
    public Category findById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
