package com.dao.impl;

import com.dao.AdvertisementDAO;
import com.domain.Advertisement;
import com.domain.Advertisement_;
import com.repository.AdvertisementRepository;
import com.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class AdvertisementDAOImpl implements AdvertisementDAO {

    @Autowired
    AdvertisementRepository repository;

    private EmailService emailService;

    @Autowired
    public AdvertisementDAOImpl(EmailService emailService) {
        this.emailService = emailService;
    }


    @Override
    public void save(Advertisement advertisement) {
        repository.save(advertisement);
        emailService.sendEmails(advertisement);
    }

    @Override
    public void update(Advertisement advertisement) {
        repository.save(advertisement);
    }

    @Override
    public Advertisement findById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Advertisement> findAdvertisementByCategory(int id) {
        return repository.findAdvertisementByCategory(id);
    }

    @Override
    public List<Advertisement> findAdvertisementByCategories(List<Integer> ids) {
        return repository.findAdvertisementByCategories(ids);
    }


    @Override
    public List<Advertisement> searchByWord(String text) {
        return repository.findAdvertisementByText(text);
    }

    @Override
    public List<Advertisement> searchByDate(LocalDate dateOfPublic) {
        return repository.findAdvertisementByDateOfPublic(dateOfPublic);
    }


    public void deleteAdvertisementByAuthor(int id) {
        repository.deleteAdvertisementByAuthor(id);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void delete() {
        repository.deleteAdvertisementByActive();
    }
}
