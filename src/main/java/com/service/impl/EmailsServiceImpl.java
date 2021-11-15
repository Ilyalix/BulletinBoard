package com.service.impl;

import com.domain.Advertisement;
import com.service.EmailService;

import javax.persistence.*;
import java.util.List;

public class EmailsServiceImpl implements EmailService {

    public static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("bulletin_board");


    @Override
    public void sendEmails(Advertisement advertisement) {
        List<String> emails = findAllSuitableEmails(advertisement);
        //send emials
        for (Object x : emails){
            System.out.println(x);
        }
    }

    private List<String> findAllSuitableEmails(Advertisement advertisement) {

        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();


        TypedQuery<String> query = em.createQuery("SELECT e.email " +
                "FROM MatchingAd m " +
                "JOIN m.author a " +
                "JOIN a.email e " +
                "WHERE m.title LIKE CONCAT('%', :ad_title, '%') " +
                "AND (:ad_price BETWEEN m.priceFrom AND m.priceTo) "+
                "AND m.category.id =: m_cat ", String.class);
        query.setParameter("ad_title", advertisement.getText());
        query.setParameter("m_cat", advertisement.getCategory().getId());
        query.setParameter("ad_price", advertisement.getPrice());

        List<String> emails = query.getResultList();
        trans.commit();

        em.close();//%text%

       return emails;
   }

//Mad, Author, Email, Category

    //Mad -> Email
}
