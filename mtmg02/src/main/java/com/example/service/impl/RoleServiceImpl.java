package com.example.service.impl;

import com.example.constant.RoleEnum;
import com.example.entities.Roles;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Roles findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Roles findByName(RoleEnum roleEnum) {
        return roleRepository.findByName(roleEnum);
    }
}
