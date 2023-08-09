package com.example.service.impl;

import com.example.entities.ShowDate;
import com.example.repository.ShowDateRepository;
import com.example.service.ShowDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowDateServiceImpl implements ShowDateService {
    @Autowired
    ShowDateRepository showDateRepository;
    @Override
    public ShowDate findByShowDate(LocalDate localDate) {
        return showDateRepository.findByShowDate(localDate);
    }

    @Override
    public ShowDate save(ShowDate showDate) {
        return showDateRepository.save(showDate);
    }

    @Override
    public List<ShowDate> findAll() {
        return showDateRepository.findAll();
    }

}
