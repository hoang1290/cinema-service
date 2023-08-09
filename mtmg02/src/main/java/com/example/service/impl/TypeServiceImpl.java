package com.example.service.impl;

import com.example.entities.Type;
import com.example.repository.TypeRepository;
import com.example.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;


    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type findById(Integer id) {
        return typeRepository.findById(id).orElse(null);
    }
}
