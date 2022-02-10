package com.controller;

import com.domain.Role;
import com.service.CRUDService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/role/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class RoleController {

    CRUDService<Role> roleService;

    @PostMapping
    public void save(@Valid @RequestBody Role role){
        roleService.save(role);
    }

    @PutMapping
    public void update(@Valid @RequestBody Role role){
        roleService.update(role);
    }

    @GetMapping(value = "{id}")
    public Role findById(@PathVariable(value = "id") int id) {
        return roleService.findById(id);
    }

    @DeleteMapping(value = "{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        roleService.deleteById(id);
    }
}

