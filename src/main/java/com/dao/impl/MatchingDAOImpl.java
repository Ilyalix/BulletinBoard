package com.dao.impl;

import com.dao.CrudDAO;
import com.domain.MatchingAd;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Repository
@Transactional
public class MatchingDAOImpl implements CrudDAO<MatchingAd> {


    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(MatchingAd matchingAd) {

        em.persist(matchingAd);

    }

    @Override
    public void update(MatchingAd matchingAd) {

        MatchingAd matchingAd1 = em.merge(matchingAd);

        em.persist(matchingAd1);

    }

    @Override
    public MatchingAd findById(int id) {

        MatchingAd matchingAd = em.find(MatchingAd.class, id);

        return matchingAd;
    }

    @Override
    public void deleteById(int id) {

        Query query =
                em.createQuery("DELETE FROM MatchingAd m WHERE m.id = :m_id");
        query.setParameter("m_id", id);
        query.executeUpdate();

    }
}
