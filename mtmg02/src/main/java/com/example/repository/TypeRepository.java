package com.example.repository;

import com.example.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {
}