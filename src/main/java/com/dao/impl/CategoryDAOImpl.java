package com.dao.impl;

import com.dao.CrudDAO;
import com.domain.Category;
import com.domain.Category_;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

public class CategoryDAOImpl implements CrudDAO<Category> {
    public static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("bulletin_board");


    @Override
    public void save(Category category) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Category> query = builder.createQuery(Category.class);

        Root<Category> root = query.from(Category.class);


//        em.persist(category);

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

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Category> query = builder.createQuery(Category.class);

        Root<Category> root = query.from(Category.class);

        Path<Integer> pathId = root.get(Category_.id);

        query.where(builder.equal(pathId, id));

        query.select(root);

        TypedQuery<Category> query1 = em.createQuery(query);
        Category category = query1.getSingleResult();

        tran.commit();

        em.close();

        return category;
    }

    @Override
    public void deleteById (int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaDelete<Category> criteriaDelete = builder.createCriteriaDelete(Category.class);

        Root<Category> root = criteriaDelete.from(Category.class);

        Path<Integer> pathId = root.get(Category_.id);

        criteriaDelete.where(builder.equal(pathId, id));

        Query query = em.createQuery(criteriaDelete);
        query.executeUpdate();

        tran.commit();

        em.close();
    }

}
