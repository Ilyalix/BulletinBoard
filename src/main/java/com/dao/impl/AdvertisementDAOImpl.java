package com.dao.impl;

import com.dao.AdvertisementDAO;
import com.service.AdvertisementService;
import com.domain.Advertisement;
import com.service.EmailService;
import com.service.impl.EmailsServiceImpl;

import javax.persistence.*;
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

        Advertisement advertisement = em.find(Advertisement.class, id);

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

       Query query =
                em.createQuery("DELETE FROM Advertisement c WHERE c.id = :a_id");
        query.setParameter("a_id", id);

        query.executeUpdate();

        tran.commit();
        em.close();
    }
}
