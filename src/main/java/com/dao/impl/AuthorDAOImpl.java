package com.dao.impl;

import com.dao.AuthorDAO;
import com.domain.*;
import com.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@Transactional
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    AuthorRepository repository;

    @Override
    public void save(Author author) {
        repository.save(author);
    }

    @Override
    public void update(Author author) {
        repository.save(author);
    }


    @Override
    public Author findById(int id) {
        return repository.findById(id).get();

    }

    @Override
    public List<Advertisement> findAdvertisementByIdAuthor(List<Integer> ids) {
        return repository.findAdvertisementByIdAuthor(ids);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
