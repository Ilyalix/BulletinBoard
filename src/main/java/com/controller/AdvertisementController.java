package com.controller;

import com.domain.Advertisement;
import com.dto.PageDTO;
import com.service.AdvertisementService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/advertisement/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AdvertisementController {

    AdvertisementService advertisementService;

    @PostMapping
    public void save(@Valid @RequestBody Advertisement advertisement) {
        advertisementService.save(advertisement);
    }

    @PutMapping
    public void update(@Valid @RequestBody Advertisement advertisement) {
        advertisementService.update(advertisement);
    }

    @GetMapping(value = "{id}")
    public Advertisement findById(@PathVariable(value = "id") int id) {
        return advertisementService.findById(id);
    }

    @GetMapping(value = "category/{id}")
    public List<Advertisement> findByCategory(@PathVariable(value = "id") int id) {
        return advertisementService.findAdvertisementByCategory(id);
    }

    @GetMapping(value = "categories/{ids}")
    public List<Advertisement> findByCategories(@PathVariable(value = "ids") List<Integer> ids) {
        return advertisementService.findAdvertisementByCategories(ids);
    }

    @GetMapping(value = "word/{text}")
    public List<Advertisement> searchByWord(@PathVariable(value = "text") String text) {
        return advertisementService.searchByWord(text);
    }

    @GetMapping(value = "date/{date}")
    public List<Advertisement> searchByDate(@PathVariable(value = "date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return advertisementService.searchByDate(localDate);
    }

    @DeleteMapping(value = "author/{id}")
    public void deleteAdvertisementByAuthor(@PathVariable(value = "id") int id) {
        advertisementService.deleteAdvertisementByAuthor(id);
    }

    @DeleteMapping(value = "{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        advertisementService.deleteById(id);
    }

    @PostMapping(value = "paging")
    public List<Advertisement> paging(@RequestBody PageDTO dto) {
        return advertisementService.paging(dto.getPage(), dto.getSize());
    }

}
