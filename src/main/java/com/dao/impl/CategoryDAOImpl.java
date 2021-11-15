package com.dao.impl;

import com.dao.CrudDAO;
import com.domain.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CategoryDAOImpl implements CrudDAO<Category> {
    public static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("bulletin_board");


    @Override
    public void save(Category category) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        em.persist(category);

        tran.commit();

        em.close();

    }

    @Override
    public void update(Category category) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        Category category1 = em.merge(category);

        em.persist(category1);

        tran.commit();

        em.close();
    }

    @Override
    public Category findById(int id) {

        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        Category category = em.find(Category.class, id);

        tran.commit();

        em.close();

        return category;
    }

    @Override
    public void deleteById (int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        Category category = em.getReference(Category.class, id);

        em.remove(category);

        tran.commit();

        em.close();
    }

}
