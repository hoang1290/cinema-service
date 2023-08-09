package com.example.repository;

import com.example.entities.ShowDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ShowDateRepository extends JpaRepository<ShowDate,Integer> {
    @Query(value = "SELECT s FROM ShowDate s WHERE s.showDate = ?1")
    ShowDate findByShowDate(LocalDate localDate);
}
