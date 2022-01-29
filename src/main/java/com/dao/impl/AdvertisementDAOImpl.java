package com.dao.impl;

import com.dao.AdvertisementDAO;
import com.domain.Advertisement;
import com.repository.AdvertisementRepository;
import com.service.EmailService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Repository
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AdvertisementDAOImpl implements AdvertisementDAO {

    AdvertisementRepository repository;

    EmailService emailService;

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
        return repository.findAdvertisementByTextContaining(text);
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
