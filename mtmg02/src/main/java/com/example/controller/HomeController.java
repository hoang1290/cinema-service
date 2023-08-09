package com.example.controller;

import com.example.entities.Account;
import com.example.entities.Movie;
import com.example.entities.Type;
import com.example.repository.SeatRepository;
import com.example.service.AccountService;
import com.example.service.MovieService;
import com.example.service.TypeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String getHome(){
        return "main";
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        List<Movie> movieList = movieService.findAll();
        model.addAttribute("movies", movieList);
        return "homePage";
    }

    @GetMapping(value = {"/403"})
    public String page403() {
        return "403";
    }


}
