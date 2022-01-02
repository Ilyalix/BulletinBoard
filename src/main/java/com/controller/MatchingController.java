package com.controller;

import com.domain.Category;
import com.domain.MatchingAd;
import com.service.CRUDService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/matchingAd/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class MatchingController {

    @Autowired
    CRUDService<MatchingAd> matchingAdService;

    @PostMapping(value = "save")
    public void save(@Valid @RequestBody MatchingAd matchingAd){
        matchingAdService.save(matchingAd);
    }

    @PutMapping(value = "put")
    public void update(@Valid @RequestBody MatchingAd matchingAd){
        matchingAdService.update(matchingAd);
    }

    @GetMapping(value = "find/{id}")
    public MatchingAd findById(@PathVariable(value = "id") int id) {
        return matchingAdService.findById(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        matchingAdService.deleteById(id);
    }
}
