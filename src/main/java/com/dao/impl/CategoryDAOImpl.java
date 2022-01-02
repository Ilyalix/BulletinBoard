package com.dao.impl;

import com.dao.CrudDAO;
import com.domain.Category;
import com.domain.Category_;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

@Repository
@Transactional
public class CategoryDAOImpl implements CrudDAO<Category> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Category category) {

        em.persist(category);

    }

    @Override
    public void update(Category category) {

        Category categoryDB = em.find(Category.class, category.getId());
        int version = categoryDB.getVersion();
        category.setVersion(version);

        Category category1 = em.merge(category);
        em.persist(category1);

    }

    @Override
    public Category findById(int id) {

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Category> query = builder.createQuery(Category.class);

        Root<Category> root = query.from(Category.class);

        Path<Integer> pathId = root.get(Category_.id);

        query.where(builder.equal(pathId, id));

        query.select(root);

        TypedQuery<Category> query1 = em.createQuery(query);
        Category category = query1.getSingleResult();

        return category;
    }

    @Override
    public void deleteById(int id) {

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaDelete<Category> criteriaDelete = builder.createCriteriaDelete(Category.class);

        Root<Category> root = criteriaDelete.from(Category.class);

        Path<Integer> pathId = root.get(Category_.id);

        criteriaDelete.where(builder.equal(pathId, id));

        Query query = em.createQuery(criteriaDelete);
        query.executeUpdate();
    }
}
