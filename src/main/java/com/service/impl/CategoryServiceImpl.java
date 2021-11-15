package com.service.impl;

import com.dao.CrudDAO;
import com.dao.impl.CategoryDAOImpl;
import com.domain.Category;
import com.service.CRUDService;
import com.validation.Validation;

public class CategoryServiceImpl implements CRUDService<Category> {

    public final CrudDAO<Category> DAO;

    public CategoryServiceImpl() {
        DAO = new CategoryDAOImpl();
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
