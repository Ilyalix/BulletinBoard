package com.service;

import com.dao.CrudDAO;
import com.domain.Advertisement;
import com.domain.Author;
import com.service.CRUDService;

import javax.persistence.*;
import java.util.List;

public interface AuthorService extends CRUDService<Author> {

   List<Advertisement> findAdvertisementByIdAuthor(List<Integer> ids);

}