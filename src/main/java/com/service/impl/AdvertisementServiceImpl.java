package com.service.impl;

import com.dao.AdvertisementDAO;
import com.domain.Advertisement;
import com.service.AdvertisementService;
import com.validation.Validation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdvertisementServiceImpl implements AdvertisementService {

    AdvertisementDAO DAO;

    @Autowired
    public AdvertisementServiceImpl(@Qualifier("advertisementDAOImpl") AdvertisementDAO DAO) {
        this.DAO = DAO;
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
    public List<Advertisement> findAdvertisementByCategories(List<Integer> ids) {
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

    @Override
    public List<Advertisement> paging(int page, int size) {
        return DAO.paging(page, size);
    }
}

/*
 * Mad("buy auto", cat - auto), save Ad("buy auto", cat - auto)
 * */


