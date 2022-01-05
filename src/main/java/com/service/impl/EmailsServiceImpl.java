package com.service.impl;

import com.domain.Advertisement;
import com.service.EmailService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;


@Repository
@Transactional
public class EmailsServiceImpl implements EmailService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void sendEmails(Advertisement advertisement) {
        List<String> emails = findAllSuitableEmails(advertisement);
        //send emials
        for (Object x : emails) {
            System.out.println(x);
        }
    }

    private List<String> findAllSuitableEmails(Advertisement advertisement) {

        TypedQuery<String> query = em.createQuery("SELECT e.email " +
                "FROM MatchingAd m " +
                "JOIN m.author a " +
                "JOIN a.email e " +
                "WHERE m.title LIKE CONCAT('%', :ad_title, '%') " +
                "AND (:ad_price BETWEEN m.priceFrom AND m.priceTo) " +
                "AND m.category.id =: m_cat ", String.class);
        query.setParameter("ad_title", advertisement.getText());
        query.setParameter("m_cat", advertisement.getCategory().getId());
        query.setParameter("ad_price", advertisement.getPrice());

        List<String> emails = query.getResultList();

        return emails;
    }
}
