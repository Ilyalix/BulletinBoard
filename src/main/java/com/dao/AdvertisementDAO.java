package com.dao;

import com.domain.Advertisement;
import com.service.CRUDService;

import java.time.LocalDate;
import java.util.List;


public interface AdvertisementDAO extends CrudDAO<Advertisement> {
    List<Advertisement> findAdvertisementByCategory(int id);

    List<Advertisement> findAdvertisementByCategories(List<Integer> ids);

    void deleteAdvertisementByAuthor(int id);

    List<Advertisement> searchByWord(String text);

    List<Advertisement> searchByDate(LocalDate dateOfPublic);

}