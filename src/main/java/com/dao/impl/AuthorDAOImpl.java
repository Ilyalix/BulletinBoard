package com.dao.impl;

import com.dao.AuthorDAO;
import com.domain.Advertisement;
import com.domain.Author;
import com.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    AuthorRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void save(Author author) {
        String password = author.getPassword();

        String encode = encoder.encode(password);

        author.setPassword(encode);

        repository.save(author);
    }

    @Override
    public void update(Author author) {
        String password = author.getPassword();

        String encode = encoder.encode(password);

        author.setPassword(encode);

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
