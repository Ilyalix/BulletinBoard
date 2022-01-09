package com.dao.impl;

import com.dao.AuthorDAO;
import com.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;


@Repository
@Transactional
public class AuthorDAOImpl implements AuthorDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(Author author) {

        em.persist(author);
    }

    @Override
    public void update(Author author) {

        Author author1 = em.merge(author);
        em.persist(author1);
    }


    @Override
    public Author findById(int id) {

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Author> query = builder.createQuery(Author.class);

        Root<Author> root = query.from(Author.class);

        Path<Integer> pathId = root.get(Author_.id);

        query.where(builder.equal(pathId, id));

        query.select(root);

        TypedQuery<Author> query1 = em.createQuery(query);
        return query1.getSingleResult();

    }

    @Override
    public List<Advertisement> findAdvertisementByIdAuthor(List<Integer> ids) {

        TypedQuery<Advertisement> query = em.createQuery("FROM Advertisement c WHERE c.author.id IN :a_ids", Advertisement.class);
        query.setParameter("a_ids", ids);
        List<Advertisement> list = query.getResultList();

        return list;
    }

    @Override
    public void deleteById(int id) {

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaDelete<Author> criteriaDelete = builder.createCriteriaDelete(Author.class);

        Root<Author> root = criteriaDelete.from(Author.class);

        Path<Integer> pathId = root.get(Author_.id);

        criteriaDelete.where(builder.equal(pathId, id));

        Query query = em.createQuery(criteriaDelete);
        query.executeUpdate();

        query.executeUpdate();
    }
}
