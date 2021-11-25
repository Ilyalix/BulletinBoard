package com.dao.impl;

import com.dao.AuthorDAO;
import com.domain.Advertisement_;
import com.domain.Author_;
import com.service.AuthorService;
import com.domain.Advertisement;
import com.domain.Author;

import javax.persistence.*;
import javax.persistence.criteria.*;
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

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Author> query = builder.createQuery(Author.class);

        Root<Author> root = query.from(Author.class);

        Path<Integer> pathId = root.get(Author_.id);

        query.where(builder.equal(pathId, id));

        query.select(root);

        TypedQuery<Author> query1 = em.createQuery(query);
        Author author = query1.getSingleResult();
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

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaDelete<Author> criteriaDelete = builder.createCriteriaDelete(Author.class);

        Root<Author> root = criteriaDelete.from(Author.class);

        Path<Integer> pathId = root.get(Author_.id);

        criteriaDelete.where(builder.equal(pathId, id));

        Query query = em.createQuery(criteriaDelete);
        query.executeUpdate();

        query.executeUpdate();

        tran.commit();
        em.close();
    }


}
