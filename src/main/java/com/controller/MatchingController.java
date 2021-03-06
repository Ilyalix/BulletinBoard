package com.controller;

import com.domain.MatchingAd;
import com.service.CRUDService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/matchingAd/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class MatchingController {

    CRUDService<MatchingAd> matchingAdService;

    @PostMapping
    public void save(@Valid @RequestBody MatchingAd matchingAd) {
        matchingAdService.save(matchingAd);
    }

    @PutMapping
    public void update(@Valid @RequestBody MatchingAd matchingAd) {
        matchingAdService.update(matchingAd);
    }

    @GetMapping(value = "{id}")
    public MatchingAd findById(@PathVariable(value = "id") int id) {
        return matchingAdService.findById(id);
    }

    @DeleteMapping(value = "{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        matchingAdService.deleteById(id);
    }
}
