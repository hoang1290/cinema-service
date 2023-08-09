package com.example.service;

import com.example.entities.ShowDate;

import java.time.LocalDate;
import java.util.List;

public interface ShowDateService {
    ShowDate findByShowDate(LocalDate localDate);

    ShowDate save(ShowDate showDate);

    List<ShowDate> findAll();
}
