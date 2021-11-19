package com.service.impl;

import com.dao.AuthorDAO;
import com.dao.impl.AuthorDAOImpl;
import com.domain.Advertisement;
import com.domain.Author;
import com.service.AuthorService;
import com.validation.Validation;

import java.util.List;


public class AuthorServiceImpl implements AuthorService {

    public final AuthorDAO DAO;

    public AuthorServiceImpl() {
        DAO = new AuthorDAOImpl();
    }

    @Override
    public void save(Author author) {
        Validation.validation(author);
        DAO.save(author);
    }

    @Override
    public void update(Author author) {
        DAO.update(author);
    }

    @Override
    public Author findById(int id) {
        return DAO.findById(id);
    }

    @Override
    public void deleteById(int id) {
        DAO.deleteById(id);
    }

    @Override
    public List<Advertisement> findAdvertisementByIdAuthor(List<Integer> ids) {
        return DAO.findAdvertisementByIdAuthor(ids);
    }
}

