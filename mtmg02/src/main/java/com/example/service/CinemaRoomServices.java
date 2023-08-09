package com.example.service;

import com.example.entities.CinemaRoom;
import com.example.entities.Seat;

import java.util.List;
import java.util.UUID;

public interface CinemaRoomServices {
    List<CinemaRoom> findAllCinemaRoom();

    CinemaRoom findById(UUID id);

    CinemaRoom createCinemaRoom(CinemaRoom cinemaRoom);

    void delete(UUID id);

    List<Seat> findAllSeatByCinema(CinemaRoom cinemaRoom);
}
