package com.example.service;

import com.example.entities.Movie;
import com.example.entities.Schedule;
import com.example.entities.Type;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MovieService {

    Movie save(Movie movie);

    Movie createMovie(MultipartFile file, Movie movie);

    Movie updateMovie(MultipartFile file, Movie movie);

    List<Movie> findAll();
    List<Movie> findByMovieName( String movieName);

    List<Movie> findByType(Integer id);

    void delete(UUID id);

    Movie findById(UUID id);

    Schedule findScheduleById(Integer scheduleId);

}
