package com.example.controller;

import com.example.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeatController {
    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/seat")
    public String seat(Model model){
        model.addAttribute("seats", seatRepository.findAll());
        return "seat";
    }
//    @Autowired
//    SeatRepository seatRepository;
//    @GetMapping("/seats")
//    public String seat(Model model){
//        model.addAttribute("seats",seatRepository.findAll());
//        return "selectSeat";
//    }
//
}
