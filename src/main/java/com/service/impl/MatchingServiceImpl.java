package com.service.impl;

import com.dao.CrudDAO;
import com.domain.MatchingAd;
import com.service.CRUDService;
import com.validation.Validation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatchingServiceImpl implements CRUDService<MatchingAd> {

    CrudDAO<MatchingAd> DAO;

    @Autowired
    public MatchingServiceImpl(CrudDAO<MatchingAd> dao) {
        this.DAO = dao;
    }

    @Override
    public void save(MatchingAd matchingAd) {
        Validation.validation(matchingAd);
        DAO.save(matchingAd);

    }

    @Override
    public void update(MatchingAd matchingAd) {
        DAO.update(matchingAd);

    }

    @Override
    public MatchingAd findById(int id) {
        return DAO.findById(id);

    }

    @Override
    public void deleteById(int id) {
        DAO.deleteById(id);
    }
}
