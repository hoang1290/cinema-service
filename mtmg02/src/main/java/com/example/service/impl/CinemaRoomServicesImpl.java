package com.example.service.impl;

import com.example.entities.CinemaRoom;
import com.example.entities.Seat;
import com.example.repository.CinemaRoomRepository;
import com.example.service.CinemaRoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CinemaRoomServicesImpl implements CinemaRoomServices {

    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;

    @Override
    public List<CinemaRoom> findAllCinemaRoom() {
        return cinemaRoomRepository.findAll();
    }

    @Override
    public CinemaRoom findById(UUID id) {
        return cinemaRoomRepository.findById(id).orElse(null);
    }

    @Override
    public CinemaRoom createCinemaRoom(CinemaRoom cinemaRoom) {
        return cinemaRoomRepository.save(cinemaRoom);
    }

    @Override
    public void delete(UUID id) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(id).orElse(null);
        cinemaRoomRepository.delete(cinemaRoom);
    }

    @Override
    public List<Seat> findAllSeatByCinema(CinemaRoom cinemaRoom) {
        return cinemaRoomRepository.findAllSeatByCinema(cinemaRoom.getSeatQuantity());
    }
}
