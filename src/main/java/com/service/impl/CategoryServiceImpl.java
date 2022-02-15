package com.service.impl;

import com.dao.CrudDAO;
import com.domain.Category;
import com.service.CRUDService;
import com.validation.Validation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CRUDService<Category> {

    CrudDAO<Category> DAO;

    @Autowired
    public CategoryServiceImpl(@Qualifier("categoryDAOImpl") CrudDAO<Category> dao) {
        this.DAO = dao;
    }

    @Override
    public void save(Category category) {
        Validation.validation(category);
        DAO.save(category);
    }

    @Override
    public void update(Category category) {
        DAO.update(category);
    }

    @Override
    public Category findById(int id) {
        return DAO.findById(id);

    }

    @Override
    public void deleteById(int id) {
        DAO.deleteById(id);
    }
}
