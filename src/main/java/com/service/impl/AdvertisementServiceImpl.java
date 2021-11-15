package com.service.impl;

import com.dao.AdvertisementDAO;
import com.service.AdvertisementService;
import com.dao.impl.AdvertisementDAOImpl;
import com.domain.Advertisement;
import com.validation.Validation;

import java.time.LocalDate;
import java.util.List;

public class AdvertisementServiceImpl implements AdvertisementService {

    public final AdvertisementDAO DAO;


    public AdvertisementServiceImpl(){
        DAO = new AdvertisementDAOImpl();
    }

    @Override
    public void save(Advertisement advertisement) {
        Validation.validation(advertisement);
        DAO.save(advertisement);
    }

    @Override
    public void update(Advertisement advertisement) {
        DAO.update(advertisement);
    }

    @Override
    public Advertisement findById(int id) {
        return DAO.findById(id);
    }

    @Override
    public void deleteById(int id) {
        DAO.deleteById(id);
    }

    @Override
    public List<Advertisement> findAdvertisementByCategory(int id) {
        return DAO.findAdvertisementByCategory(id);
    }
    
    @Override
    public List<Advertisement> findAdvertisementByCategories(List<Integer> ids){
        return DAO.findAdvertisementByCategories(ids);
    }

    @Override
    public void deleteAdvertisementByAuthor(int id) {
        DAO.deleteAdvertisementByAuthor(id);

    }

    @Override
    public List<Advertisement> searchByWord(String text) {
        return DAO.searchByWord(text);
    }

    @Override
    public List<Advertisement> searchByDate(LocalDate dateOfPublic) {
        return DAO.searchByDate(dateOfPublic);
    }
}

/*
* Mad("buy auto", cat - auto), save Ad("buy auto", cat - auto)
* */


