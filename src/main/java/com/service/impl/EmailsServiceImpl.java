package com.service.impl;

import com.domain.Advertisement;
import com.service.EmailService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.IntFunction;


@Repository
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailsServiceImpl implements EmailService {

    @PersistenceContext
    EntityManager em;

    final JavaMailSender sender;

    @Autowired
    public EmailsServiceImpl(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public void sendEmails(Advertisement advertisement) {
        String[] emails = findAllSuitableEmails(advertisement);

        Query queryPhones = em.createQuery("SELECT p.phone FROM Author a JOIN a.phones p WHERE a.id =:a_id");
        queryPhones.setParameter("a_id", advertisement.getAuthor().getId());
        List phones = queryPhones.getResultList();

        Query queryName = em.createQuery("SELECT a.name FROM Author a WHERE a.id =:a_id");
        queryName.setParameter("a_id", advertisement.getAuthor().getId());
        List name = queryName.getResultList();

        if (emails.length > 0) {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(emails);
            message.setSubject("Greetings");
            message.setText("Added new ad with text " + advertisement.getText() +
                    "\n price: " + advertisement.getPrice() +
                    "\n Author Name: " + name.get(0) +
                    "\n Author Phone: " + phones);

            sender.send(message);
        }
    }


    private String[] findAllSuitableEmails(Advertisement advertisement) {

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

        IntFunction<String[]> function = size -> new String[size];

        return query.getResultStream().toArray(function);
    }
}
