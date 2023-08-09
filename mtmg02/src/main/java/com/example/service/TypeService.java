package com.example.service;

import com.example.entities.Type;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TypeService {
    List<Type> findAll();

    Type findById(Integer id);
}
