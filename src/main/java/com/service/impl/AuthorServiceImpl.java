package com.service.impl;

import com.dao.AuthorDAO;
import com.dao.impl.AuthorDAOImpl;
import com.domain.Advertisement;
import com.domain.Author;
import com.service.AuthorService;
import com.validation.Validation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorServiceImpl implements AuthorService {

    AuthorDAO DAO;

    @Autowired
    public AuthorServiceImpl(@Qualifier("authorDAOImpl") AuthorDAO DAO) {
        this.DAO = DAO;
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

