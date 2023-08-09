package com.example.repository;

import com.example.entities.CinemaRoom;
import com.example.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, UUID> {
    @Query(value = "SELECT * FROM seat LIMIT ?1", nativeQuery = true)
    List<Seat> findAllSeatByCinema(Integer quantity);

}
