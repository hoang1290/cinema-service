package com.example.repository;

import com.example.entities.CinemaRoom;
import com.example.entities.ScheduleSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeat,UUID> {

    @Query(value = "SELECT s FROM ScheduleSeat s " +
            "JOIN s.movie sm JOIN s.schedule ss JOIN s.seat sss JOIN s.cinemaRoom sc " +
            "WHERE sm.id = ?1 " +
            "AND ss.id = ?2 " +
            "AND sss.id = ?3 " +
            "AND sc.id = ?4 " )

    ScheduleSeat findByMovieIdAndScheduleIdAndSeatIdAndCinemaId(UUID movieId, Integer scheduleId, Integer seatId, UUID cinemaId );

    @Query(value = "SELECT s FROM ScheduleSeat s join s.cinemaRoom sc Join s.seat ss WHERE sc.id = ?1 AND ss.id = ?2 AND s.schedule IS NULL")
    Optional<ScheduleSeat> findByCinemaRoomAndSeat(UUID cinemaId, Integer seatId);
    @Query(value = "SELECT s FROM ScheduleSeat s join s.cinemaRoom sc WHERE sc = ?1 ORDER BY s.seat.id")
    List<ScheduleSeat> findByCinemaRoom(CinemaRoom cinemaRoom);
}
