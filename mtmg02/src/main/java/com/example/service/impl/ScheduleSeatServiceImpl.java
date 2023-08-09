package com.example.service.impl;

import com.example.entities.CinemaRoom;
import com.example.entities.ScheduleSeat;
import com.example.repository.ScheduleSeatRepository;
import com.example.service.ScheduleSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ScheduleSeatServiceImpl implements ScheduleSeatService {
    @Autowired
    private ScheduleSeatRepository scheduleSeatRepository;

    @Override
    public ScheduleSeat save(ScheduleSeat scheduleSeat) {
        return scheduleSeatRepository.save(scheduleSeat);
    }

    @Override
    public ScheduleSeat findByCinemaRoomAndSeat(UUID cinemaId, Integer seatId) {
        return scheduleSeatRepository.findByCinemaRoomAndSeat(cinemaId,seatId).orElse(null);
    }

    @Override
    public ScheduleSeat findByMovieIdAndScheduleIdAndSeatIdAndCinemaId(UUID movieId, Integer scheduleId, Integer seatId, UUID cinemaId) {
        return scheduleSeatRepository.findByMovieIdAndScheduleIdAndSeatIdAndCinemaId(movieId,scheduleId,seatId,cinemaId);
    }

    @Override
    public List<ScheduleSeat> findByCinemaRoom(CinemaRoom cinemaRoom) {
        return scheduleSeatRepository.findByCinemaRoom(cinemaRoom);
    }
}
