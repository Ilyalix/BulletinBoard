package com.service.impl;

import com.domain.MatchingAd;
import com.service.MatchingAdService;

import javax.persistence.*;


public class MatchingServiceImpl implements MatchingAdService {

    public static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("bulletin_board");


    @Override
    public void save(MatchingAd matchingAd) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();

        em.persist(matchingAd);

        trans.commit();

        em.close();
    }

    @Override
    public void update(MatchingAd matchingAd) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        MatchingAd matchingAd1 = em.merge(matchingAd);

        em.persist(matchingAd1);

        tran.commit();

        em.close();
    }

    @Override
    public MatchingAd findById(int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction tran = em.getTransaction();

        tran.begin();

        MatchingAd matchingAd = em.find(MatchingAd.class, id);

        tran.commit();

        em.close();

        return matchingAd;
    }

    @Override
    public void deleteById(int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction trans = em.getTransaction();

        Query query =
                em.createQuery("DELETE FROM MatchingAd m WHERE m.id = :m_id");
        query.setParameter("m_id", id);

        trans.commit();

        em.close();
    }
}
