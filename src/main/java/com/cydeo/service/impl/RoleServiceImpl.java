package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;

import java.util.List;

public class RoleServiceImpl extends AbstractMapService<RoleDTO,Long> implements RoleService {
    @Override
    public RoleDTO save(RoleDTO role) { //we changed the parameter name to role(it is user in CrudService)
        return super.save(role.getId(),role);
    }

    @Override
    public RoleDTO findById(Long id) { //we changed the parameter name to id (was username in CrudService)
        return super.findById(id);
    }

    @Override
    public List<RoleDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long username) {

    }
}
