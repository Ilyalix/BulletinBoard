package com.dao.impl;

import com.dao.CrudDAO;
import com.domain.MatchingAd;
import com.repository.MatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Repository
@Transactional
public class MatchingDAOImpl implements CrudDAO<MatchingAd> {

    @Autowired
    MatchingRepository repository;

    @Override
    public void save(MatchingAd matchingAd) {
        repository.save(matchingAd);
    }

    @Override
    public void update(MatchingAd matchingAd) {
        repository.save(matchingAd);
    }

    @Override
    public MatchingAd findById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
