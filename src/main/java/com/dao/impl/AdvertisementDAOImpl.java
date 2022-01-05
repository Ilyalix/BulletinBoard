package com.dao.impl;

import com.dao.AdvertisementDAO;
import com.domain.Advertisement;
import com.domain.Advertisement_;
import com.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;


@Repository
@Transactional
public class AdvertisementDAOImpl implements AdvertisementDAO {

    @PersistenceContext
    private EntityManager em;


    private EmailService emailService;

    @Autowired
    public AdvertisementDAOImpl(EmailService emailService) {
        this.emailService = emailService;
    }


    @Override
    public void save(Advertisement advertisement) {

        em.persist(advertisement);
        emailService.sendEmails(advertisement);

    }

    @Override
    public void update(Advertisement advertisement) {

        Advertisement advertisementNew = em.merge(advertisement);
        em.persist(advertisementNew);

    }

    @Override
    public Advertisement findById(int id) {

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Advertisement> query = builder.createQuery(Advertisement.class);

        Root<Advertisement> root = query.from(Advertisement.class);

        Path<Integer> pathId = root.get(Advertisement_.id);

        query.where(builder.equal(pathId, id));

        query.select(root);

        TypedQuery<Advertisement> query1 = em.createQuery(query);
        Advertisement advertisement = query1.getSingleResult();

        return advertisement;
    }

    @Override
    public List<Advertisement> findAdvertisementByCategory(int id) {

        TypedQuery<Advertisement> query =
                em.createQuery("FROM Advertisement c WHERE c.category.id = :a_id", Advertisement.class);
        query.setParameter("a_id", id);

        List<Advertisement> list = query.getResultList();

        return list;
    }

    @Override
    public List<Advertisement> findAdvertisementByCategories(List<Integer> ids) {

        TypedQuery<Advertisement> query =
                em.createQuery("FROM Advertisement c WHERE c.category.id IN :a_ids", Advertisement.class);
        query.setParameter("a_ids", ids);

        List<Advertisement> list = query.getResultList();

        return list;
    }


    @Override
    public List<Advertisement> searchByWord(String text) {

        TypedQuery<Advertisement> query =
                em.createQuery("FROM Advertisement c WHERE c.text LIKE '%' || :text || '%'", Advertisement.class);    // '%text%'
        query.setParameter("text", text);

        List<Advertisement> list = query.getResultList();

        return list;

    }

    @Override
    public List<Advertisement> searchByDate(LocalDate dateOfPublic) {

        TypedQuery<Advertisement> query =
                em.createQuery("FROM Advertisement c WHERE c.dateOfPublic = :date", Advertisement.class);
        query.setParameter("date", dateOfPublic);

        List<Advertisement> list = query.getResultList();

        return list;

    }


    public void deleteAdvertisementByAuthor(int id) {

        Query query =
                em.createQuery("DELETE FROM Advertisement c WHERE c.author.id = :a_id");
        query.setParameter("a_id", id);

        query.executeUpdate();

    }

    @Override
    public void deleteById(int id) {

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaDelete<Advertisement> criteriaDelete = builder.createCriteriaDelete(Advertisement.class);

        Root<Advertisement> root = criteriaDelete.from(Advertisement.class);

        Path<Integer> pathId = root.get(Advertisement_.id);

        criteriaDelete.where(builder.equal(pathId, id));

        Query query = em.createQuery(criteriaDelete);
        query.executeUpdate();

        query.executeUpdate();
    }
}
















/*
    @Override
    public List findAdvertisementByIdAuthor(int... ids) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        List<Integer> names = new ArrayList<>();
        List<Author> authors = new ArrayList<>();


        for (Integer x : ids) {
            names.add(x);
            Author author = em.find(Author.class, x);
            authors.add(author);
        }

        Query query = em.createQuery("FROM Advertisement c WHERE author IN :h_id");
        query.setParameter("h_id", authors);
        List<Advertisement> list = query.getResultList();


        tran.commit();
        em.close();

        return list;
    }*/