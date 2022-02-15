package com.controller;


import com.domain.Category;
import com.service.CRUDService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/category/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class CategoryController {

    CRUDService<Category> categoryService;

    @Secured("ROLE_ADMIN")
    @PostMapping
    public void save(@Valid @RequestBody Category category) {
        categoryService.save(category);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public void update(@Valid @RequestBody Category category) {
        categoryService.update(category);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping(value = "{id}")
    public Category findById(@PathVariable(value = "id") int id) {
        return categoryService.findById(id);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        categoryService.deleteById(id);
    }
}
