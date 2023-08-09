package com.example.controller;

import com.example.entities.ShowDate;
import com.example.service.MovieService;
import com.example.service.ShowDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Controller
public class ShowDateController {
    @Autowired
    private ShowDateService showDateService;
    @Autowired
    private MovieService movieService;
    @GetMapping("/add-show-date")
    public String getShowDate(Model model){
        model.addAttribute("movies",movieService.findAll());
        return "addShowDate";
    }
    @GetMapping("/show-date")
    public String getShowDateList(Model model){
        model.addAttribute("showDates",showDateService.findAll());
        return "showDateList";
    }

    @PostMapping("/create-show-date")
    public String createShowDate(Model model,
                                 @RequestParam("localDate")LocalDate localDate,
                                 @RequestParam("movieId")UUID movieId){
        ShowDate showDate = showDateService.findByShowDate(localDate);
        if(showDate == null){
            showDate = new ShowDate();
            showDate.setShowDate(localDate);
            showDate.setMovies(new ArrayList<>());
            showDateService.save(showDate);
        }
        showDate.getMovies().add(movieService.findById(movieId));
        showDateService.save(showDate);
        model.addAttribute("showDates",showDateService.findAll());
        return "showDateList";
    }
}
