package com.dao;

import com.dao.CrudDAO;
import com.domain.Advertisement;
import com.domain.Author;

import java.util.List;

public interface AuthorDAO extends CrudDAO<Author> {

   List<Advertisement> findAdvertisementByIdAuthor(List<Integer> ids);


}