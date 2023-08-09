package com.example.controller;

import com.example.constant.RoleEnum;
import com.example.constant.SeatStatus;
import com.example.constant.SeatType;
import com.example.entities.CinemaRoom;
import com.example.entities.ScheduleSeat;
import com.example.entities.Seat;
import com.example.service.CinemaRoomServices;
import com.example.service.ScheduleSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class CinemaRoomController {

    @Autowired
    private CinemaRoomServices cinemaRoomServices;
    @Autowired
    private ScheduleSeatService scheduleSeatService;

    @GetMapping("/cinemaRoom")
    public String getCinemaRoom(Model model) {
        model.addAttribute("cinemaRooms", cinemaRoomServices.findAllCinemaRoom());
        return "cinemaRoom";
    }

    @GetMapping("/addCinemaRoom")
    public String getAddCinemaRoom(Model model) {
        model.addAttribute("addCinemaRoom", new CinemaRoom());
        return "addCinemaRoom";
    }

    @PostMapping("/addCinemaRoom")
    public String createCinemaRoom(
            Model model,
            @ModelAttribute("addCinemaRoom") CinemaRoom cinemaRoom
    ) {
        cinemaRoomServices.createCinemaRoom(cinemaRoom);
        model.addAttribute("cinemaRooms", cinemaRoomServices.findAllCinemaRoom());
        return "cinemaRoom";
    }

    @GetMapping("/deleteCinemaRoom/{id}")
    public String deleteCinemaRoom(
            Model model,
            @PathVariable UUID id
    ) {
        cinemaRoomServices.delete(id);
        model.addAttribute("cinemaRooms", cinemaRoomServices.findAllCinemaRoom());
        return "cinemaRoom";
    }


    @GetMapping("/selection-seat/{id}")
    public String selectionSeat(Model model,
                                @PathVariable UUID id) {
        CinemaRoom cinemaRoom = cinemaRoomServices.findById(id);
        List<Seat> seats = cinemaRoomServices.findAllSeatByCinema(cinemaRoom);

        for (Seat seat : seats) {
            ScheduleSeat scheduleSeat = scheduleSeatService.findByCinemaRoomAndSeat(cinemaRoom.getId(), seat.getId());
            if (scheduleSeat == null) {
                scheduleSeat = new ScheduleSeat();
                scheduleSeat.setSeat(seat);
                scheduleSeat.setSeatStatus(SeatStatus.NOT_RESERVED);
                scheduleSeat.setSeatType(SeatType.NORMAL);
                scheduleSeat.setCinemaRoom(cinemaRoom);
                scheduleSeatService.save(scheduleSeat);
            }
        }
        List<ScheduleSeat> scheduleSeatList = scheduleSeatService.findByCinemaRoom(cinemaRoom);
        model.addAttribute("scheduleSeatList", scheduleSeatList);
        model.addAttribute("cinemaId", id);
        return "selectionSeat";
    }

    @PostMapping("/edit-cinema/{id}")
    public String editSelectionSeat(Model model,
                                    @PathVariable UUID id,
                                    @RequestParam("selected-seat") List<Integer> selectedSeat) {
        List<Seat> seats = cinemaRoomServices.findAllSeatByCinema(cinemaRoomServices.findById(id));

        for (Seat seat : seats) {
            ScheduleSeat scheduleSeat = scheduleSeatService.findByCinemaRoomAndSeat(id, seat.getId());
            if (scheduleSeat != null) {
                scheduleSeat.setSeatType(SeatType.NORMAL);
                scheduleSeatService.save(scheduleSeat);
            }
        }
        for (Integer seatId : selectedSeat) {
            ScheduleSeat scheduleSeat = scheduleSeatService.findByCinemaRoomAndSeat(id, seatId);
            scheduleSeat.setSeatType(SeatType.VIP);
            scheduleSeatService.save(scheduleSeat);
        }
        model.addAttribute("cinemaRooms", cinemaRoomServices.findAllCinemaRoom());
        return "cinemaRoom";
    }


}
