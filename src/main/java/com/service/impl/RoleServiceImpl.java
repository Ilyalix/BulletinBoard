package com.service.impl;

import com.dao.CrudDAO;
import com.dao.impl.RoleDAOImpl;
import com.domain.Role;
import com.service.CRUDService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements CRUDService<Role> {

    CrudDAO<Role> DAO;

    @Autowired
    public RoleServiceImpl(CrudDAO<Role> dao) {
        this.DAO = dao;
    }

    @Override
    public void save(Role role) {
        DAO.save(role);
    }

    @Override
    public void update(Role role) {
        DAO.update(role);
    }

    @Override
    public Role findById(int id) {
        return DAO.findById(id);
    }

    @Override
    public void deleteById(int id) {
        DAO.deleteById(id);
    }
}
