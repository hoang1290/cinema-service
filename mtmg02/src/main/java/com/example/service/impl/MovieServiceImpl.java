package com.example.service.impl;

import com.example.entities.CinemaRoom;
import com.example.entities.Movie;
import com.example.entities.Schedule;
import com.example.entities.Type;
import com.example.filestorage.MyFile;
import com.example.repository.MovieRepository;
import com.example.service.MovieService;
import com.example.service.MyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    private MyFileService myFileService;
    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie createMovie(MultipartFile file, Movie movie) {

        MyFile myFile = new MyFile();
        myFile.setContentType(file.getContentType());
        myFile.setFileName(file.getOriginalFilename());
        try {
            myFile.setContent(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        myFileService.save(myFile);
        movie.setSmallImage(myFile);
        movie.setLargeImage(myFile);
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(MultipartFile file, Movie movie) {
        MyFile myFile = new MyFile();
        myFile.setId(findById(movie.getId()).getLargeImage().getId());
        myFile.setContentType(file.getContentType());
        myFile.setFileName(file.getOriginalFilename());
        try {
            myFile.setContent(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        myFileService.save(myFile);
        movie.setSmallImage(myFile);
        movie.setLargeImage(myFile);

        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> findByMovieName(String movieName) {
        return movieRepository.findByMovieName(movieName);
    }

    @Override
    public List<Movie> findByType(Integer id) {
        return movieRepository.findByType(id);
    }

    @Override
    public void delete(UUID id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        movieRepository.delete(movie);
    }

    @Override
    public Movie findById(UUID id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Schedule findScheduleById(Integer scheduleId) {
        return movieRepository.findScheduleById(scheduleId);
    }

}
