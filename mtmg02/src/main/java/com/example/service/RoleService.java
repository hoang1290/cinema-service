package com.example.service;

import com.example.constant.RoleEnum;
import com.example.entities.Roles;

public interface RoleService {
    Roles findById(Integer id);
    Roles findByName(RoleEnum roleEnum);
}
