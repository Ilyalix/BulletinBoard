package com.service;

import com.domain.Advertisement;

import java.time.LocalDate;
import java.util.List;


public interface AdvertisementService extends CRUDService<Advertisement> {
    List<Advertisement> findAdvertisementByCategory(int id);

    List<Advertisement> findAdvertisementByCategories(List<Integer> ids);

    void deleteAdvertisementByAuthor(int id);

    List<Advertisement> searchByWord(String text);

    List<Advertisement> searchByDate(LocalDate dateOfPublic);

    List<Advertisement> paging(int page, int size);
}