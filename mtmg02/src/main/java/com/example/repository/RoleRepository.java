package com.example.repository;

import com.example.constant.RoleEnum;
import com.example.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Roles findByName(RoleEnum roleEnum);
}
