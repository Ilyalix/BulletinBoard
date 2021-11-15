package com.dao.impl;

import com.dao.AuthorDAO;
import com.service.AuthorService;
import com.domain.Advertisement;
import com.domain.Author;

import javax.persistence.*;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {

    public static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("bulletin_board");

    @Override
    public void save(Author author) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();

        em.persist(author);

        trans.commit();

        em.close();

    }


    @Override
    public void update(Author author) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        Author author1 = em.merge(author);

        em.persist(author1);

        tran.commit();

        em.close();
    }



    @Override
    public Author findById(int id) {

        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        Author author = em.find(Author.class, id);

        tran.commit();

        em.close();

        return author;
    }

   @Override
    public List<Advertisement> findAdvertisementByIdAuthor(List<Integer> ids) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        TypedQuery<Advertisement> query = em.createQuery("FROM Advertisement c WHERE c.author.id IN :a_ids", Advertisement.class);
        query.setParameter("a_ids", ids);
        List<Advertisement> list = query.getResultList();

        tran.commit();
        em.close();

        return list;
    }

    @Override
    public void deleteById(int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        Author author = em.find(Author.class, id);
        em.remove(author);

        tran.commit();
        em.close();
    }


}
