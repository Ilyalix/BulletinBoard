package com.dao.impl;

import com.dao.CrudDAO;
import com.domain.Role;
import com.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDAOImpl implements CrudDAO<Role> {

    @Autowired
    RoleRepository repository;

    @Override
    public void save(Role role) {
        repository.save(role);
    }

    @Override
    public void update(Role role) {
        repository.save(role);
    }

    @Override
    public Role findById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
