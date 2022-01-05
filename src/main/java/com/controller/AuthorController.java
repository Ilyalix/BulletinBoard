package com.controller;

import com.domain.Advertisement;
import com.domain.Author;
import com.service.AuthorService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/author/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping(value = "save")
    public void save(@Valid @RequestBody Author author){
        authorService.save(author);
    }

    @PutMapping(value = "update")
    public void update(@Valid @RequestBody Author author){
        authorService.update(author);
    }

    @GetMapping(value = "find/{id}")
    public Author findById(@PathVariable(value = "id") int id) {
        return authorService.findById(id);
    }

    @GetMapping(value = "findAdvertisementByIdAuthor/{ids}")
    public List<Advertisement> findAdvertisementByIdAuthor(@PathVariable(value = "ids") List<Integer> ids) {
        return authorService.findAdvertisementByIdAuthor(ids);
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        authorService.deleteById(id);
    }


}
