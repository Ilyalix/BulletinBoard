package com.controller;

import com.domain.Advertisement;
import com.domain.Author;
import com.service.AuthorService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/author/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AuthorController {

    AuthorService authorService;

    @Secured({"ROLE_USER"})
    @PostMapping(value = "save")
    public void save(@Valid @RequestBody Author author){
        authorService.save(author);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping
    public void update(@Valid @RequestBody Author author){
        authorService.update(author);
    }

    @Secured({"ROLE_USER"})
    @GetMapping(value = "{id}")
    public Author findById(@PathVariable(value = "id") int id) {
        return authorService.findById(id);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping(value = "get-ad-author/{ids}")
    public List<Advertisement> findAdvertisementByIdAuthor(@PathVariable(value = "ids") List<Integer> ids) {
        return authorService.findAdvertisementByIdAuthor(ids);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @DeleteMapping(value = "{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        authorService.deleteById(id);
    }
}
