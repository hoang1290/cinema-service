package com.example.service;

import com.example.entities.CinemaRoom;
import com.example.entities.ScheduleSeat;

import java.util.List;
import java.util.UUID;

public interface ScheduleSeatService  {
    ScheduleSeat save(ScheduleSeat scheduleSeat);

    ScheduleSeat findByCinemaRoomAndSeat(UUID cinemaId, Integer seatId);

    ScheduleSeat findByMovieIdAndScheduleIdAndSeatIdAndCinemaId(UUID movieId, Integer scheduleId, Integer seatId, UUID cinemaId);

    List<ScheduleSeat> findByCinemaRoom(CinemaRoom cinemaRoom);

}
