package com.controller;

import com.domain.Advertisement;
import com.domain.Category;
import com.service.AdvertisementService;
import com.service.CRUDService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/advertisement/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AdvertisementController {

    @Autowired
    AdvertisementService advertisementService;


    @PostMapping(value = "save")
    public void save(@Valid @RequestBody Advertisement advertisement){
        advertisementService.save(advertisement);
    }

    @PutMapping(value = "update")
    public void update(@Valid @RequestBody Advertisement advertisement){
        advertisementService.update(advertisement);
    }

    @GetMapping(value = "find/{id}")
    public Advertisement findById(@PathVariable(value = "id") int id) {
        return advertisementService.findById(id);
    }

    @GetMapping(value = "findByCategory/{id}")
    public List<Advertisement> findByCategory(@PathVariable(value = "id") int id) {
        return advertisementService.findAdvertisementByCategory(id);
    }

    @GetMapping(value = "findByCategories/{ids}")
    public List<Advertisement> findByCategories(@PathVariable(value = "ids") List<Integer> ids) {
        return advertisementService.findAdvertisementByCategories(ids);
    }

    @GetMapping(value = "searchByWord/{text}") //
    public List<Advertisement> searchByWord(@PathVariable(value = "text") String text) {
        return advertisementService.searchByWord(text);
    }

    @GetMapping(value = "searchByDate/{date}") //
    public List<Advertisement> searchByDate(@PathVariable(value = "date") String date) {

        LocalDate localDate = LocalDate.parse(date);

        return advertisementService.searchByDate(localDate);
    }

    @DeleteMapping(value = "deleteAdvertisementByAuthor/{id}")
    public void deleteAdvertisementByAuthor(@PathVariable(value = "id") int id) {
        advertisementService.deleteAdvertisementByAuthor(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        advertisementService.deleteById(id);
    }
}
