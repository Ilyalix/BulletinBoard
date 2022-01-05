package com.controller;

import com.domain.Advertisement;
import com.domain.Author;
import com.domain.Category;
import com.service.AuthorService;
import com.service.CRUDService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/category/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class CategoryController {

    @Autowired
    CRUDService<Category> categoryService;

    @PostMapping(value = "save")
    public void save(@Valid @RequestBody Category category){
        categoryService.save(category);
    }

    @PutMapping(value = "update")
    public void update(@Valid @RequestBody Category category){
        categoryService.update(category);
    }

    @GetMapping(value = "find/{id}")
    public Category findById(@PathVariable(value = "id") int id) {
        return categoryService.findById(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        categoryService.deleteById(id);
    }
}
