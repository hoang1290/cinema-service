package com.example.repository;

import com.example.entities.Movie;
import com.example.entities.Schedule;
import com.example.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    @Query(value = "SELECT m FROM Movie m JOIN m.types mt WHERE mt.id = ?1")
    List<Movie> findByType(Integer id);


    @Query(value = "FROM Movie m WHERE m.id = ?1")
    Movie findByCinemaRoomId(UUID id);

    @Query(value = "FROM Movie m WHERE m.movieNameEnglish LIKE %:name% OR m.movieNameVn LIKE %:name% ")
    List<Movie> findByMovieName(@Param("name") String movieName);


    @Query(value = "SELECT ms FROM Movie m JOIN m.schedules ms WHERE ms.id = ?1 ")
    Schedule findScheduleById(Integer scheduleId);

}
