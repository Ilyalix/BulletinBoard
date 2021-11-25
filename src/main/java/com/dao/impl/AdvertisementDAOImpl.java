package com.dao.impl;

import com.dao.AdvertisementDAO;
import com.domain.Advertisement_;
import com.domain.Category;
import com.domain.Category_;
import com.service.AdvertisementService;
import com.domain.Advertisement;
import com.service.EmailService;
import com.service.impl.EmailsServiceImpl;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;

public class AdvertisementDAOImpl implements AdvertisementDAO {

    public static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("bulletin_board");

    private EmailService emailService;

    public AdvertisementDAOImpl() {
        emailService = new EmailsServiceImpl();
    }


    @Override
    public void save(Advertisement advertisement) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();

        em.persist(advertisement);

        emailService.sendEmails(advertisement);

        trans.commit();

        em.close();
    }

    @Override
    public void update(Advertisement advertisement) {

        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        Advertisement advertisement1 = em.merge(advertisement);

        em.persist(advertisement1);

        tran.commit();

        em.close();
    }

    @Override
    public Advertisement findById(int id) {

        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Advertisement> query = builder.createQuery(Advertisement.class);

        Root<Advertisement> root = query.from(Advertisement.class);

        Path<Integer> pathId = root.get(Advertisement_.id);

        query.where(builder.equal(pathId, id));

        query.select(root);

        TypedQuery<Advertisement> query1 = em.createQuery(query);
        Advertisement advertisement = query1.getSingleResult();

        tran.commit();

        em.close();

        return advertisement;
    }

    @Override
    public List<Advertisement> findAdvertisementByCategory(int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        TypedQuery<Advertisement> query =
                em.createQuery("FROM Advertisement c WHERE c.category.id = :a_id", Advertisement.class);
        query.setParameter("a_id", id);

        List<Advertisement> list = query.getResultList();

        tran.commit();
        em.close();

        return list;
    }

    @Override
    public List<Advertisement> findAdvertisementByCategories(List<Integer> ids) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();


        TypedQuery<Advertisement> query =
                em.createQuery("FROM Advertisement c WHERE c.category.id IN :a_ids", Advertisement.class);
        query.setParameter("a_ids", ids);

        List<Advertisement> list = query.getResultList();

        tran.commit();
        em.close();

        return list;
    }



    @Override
    public List<Advertisement> searchByWord(String text) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        TypedQuery<Advertisement> query =
                em.createQuery("FROM Advertisement c WHERE c.text LIKE '%' || :text || '%'", Advertisement.class);    // '%text%'
        query.setParameter("text", text);

        List<Advertisement> list = query.getResultList();

        tran.commit();
        em.close();

        return list;

    }

    @Override
    public List<Advertisement> searchByDate(LocalDate dateOfPublic) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        TypedQuery<Advertisement> query =
                em.createQuery("FROM Advertisement c WHERE c.dateOfPublic LIKE :date", Advertisement.class);
        query.setParameter("date", dateOfPublic);

        List<Advertisement> list = query.getResultList();

        tran.commit();
        em.close();

        return list;

    }


    public void deleteAdvertisementByAuthor(int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        Query query =
                em.createQuery("DELETE FROM Advertisement c WHERE c.author.id = :a_id");
        query.setParameter("a_id", id);

        query.executeUpdate();

        tran.commit();
        em.close();

    }

    @Override
    public void deleteById(int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaDelete<Advertisement> criteriaDelete = builder.createCriteriaDelete(Advertisement.class);

        Root<Advertisement> root = criteriaDelete.from(Advertisement.class);

        Path<Integer> pathId = root.get(Advertisement_.id);

        criteriaDelete.where(builder.equal(pathId, id));

        Query query = em.createQuery(criteriaDelete);
        query.executeUpdate();

        query.executeUpdate();

        tran.commit();
        em.close();
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