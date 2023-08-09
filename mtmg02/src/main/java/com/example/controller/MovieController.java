package com.example.controller;

import com.example.auth.CustomUserDetails;
import com.example.constant.SeatStatus;
import com.example.constant.SeatType;
import com.example.entities.*;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaRoomServices cinemaRoomServices;
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleSeatService scheduleSeatService;

    @Autowired
    private ShowDateService showDateService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/search-movie")
    public String getMovieList(Model model, @RequestParam String movieName) {
        model.addAttribute("movies", movieService.findByMovieName(movieName));
        return "searchMovieList";
    }

    @GetMapping("/showtime")
    public String showTimeMovie(
            @RequestParam(name = "date", required = false) String date,
            Model model
    ) {

        // Get list Date will be shown
        LocalDate showDate = parseLocalDate(date);
        List<LocalDate> listShowDate = generateNextDatesFromNow(5);
        ShowDate showDates = showDateService.findByShowDate(showDate);
        if(showDates == null){
            showDates = new ShowDate();
            showDates.setShowDate(showDate);
            showDateService.save(showDates);
        }

        model.addAttribute("listDate", listShowDate);
        model.addAttribute("showDates", showDates);
        return "showtimes";
    }

    private LocalDate parseLocalDate(String localDateStr) {
        if (StringUtils.isEmpty(localDateStr)) {
            return LocalDate.now();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(localDateStr, formatter);
        return localDate;
    }

    private List<LocalDate> generateNextDatesFromNow(int nextDate) {
        List<LocalDate> listDate = new ArrayList<>();
        LocalDate now = LocalDate.now();
        listDate.add(now);
        for (int i = 1; i <= nextDate; i++) {
            listDate.add(now.plusDays(i));
        }

        return listDate;
    }

    @GetMapping("/movieList")
    public String getMovieList(Model model) {
        model.addAttribute("movieList", movieService.findAll());
        return "movieList";
    }


    @GetMapping("/addMovie")
    public String getAddMovie(Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("scheduleList", scheduleService.findAll());
        model.addAttribute("movie", new Movie());
        model.addAttribute("cinemaRooms", cinemaRoomServices.findAllCinemaRoom());
        return "addMovie";
    }

    @PostMapping("/create-movie")
    public String createMovie(
            Model model,
            @RequestParam("image") MultipartFile file,
            @RequestParam("category") List<Integer> categoryIdList,
            @RequestParam("time") List<Integer> timeIdList,
            @RequestParam("cinemaId") UUID cinemaId,
            @ModelAttribute() Movie movie
    ) {
        List<Schedule> scheduleList = new ArrayList<>();
        for (Integer a : timeIdList) {
            scheduleList.add(scheduleService.findById(a));
        }
        movie.setSchedules(scheduleList);

        movie.setCinemaRoom(cinemaRoomServices.findById(cinemaId));

        List<Type> typeList = new ArrayList<>();
        for (Integer a : categoryIdList) {
            typeList.add(typeService.findById(a));
        }
        movie.setTypes(typeList);
        movieService.createMovie(file, movie);
        model.addAttribute("movieList", movieService.findAll());
        return "movieList";
    }

    @GetMapping("/editMovie/{id}")
    public String showEditMovie(Model model, @PathVariable UUID id) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("scheduleList", scheduleService.findAll());
        model.addAttribute("movie", movieService.findById(id));
        model.addAttribute("cinemaRooms", cinemaRoomServices.findAllCinemaRoom());
        return "editMovie";
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(
            Model model,
            @PathVariable UUID id
    ) {
        movieService.delete(id);
        model.addAttribute("movieList", movieService.findAll());
        return "movieList";
    }

    @PostMapping("/edit-movie")
    public String editMovie(Model model,
                            @RequestParam("image") MultipartFile file,
                            @RequestParam("category") List<Integer> categoryIdList,
                            @RequestParam("time") List<Integer> timeIdList,
                            @RequestParam("cinemaId") UUID cinemaId,
                            @ModelAttribute() Movie movie) {
        List<Schedule> scheduleList = new ArrayList<>();
        for (Integer a : timeIdList) {
            scheduleList.add(scheduleService.findById(a));
        }
        movie.setSchedules(scheduleList);

        movie.setCinemaRoom(cinemaRoomServices.findById(cinemaId));

        List<Type> typeList = new ArrayList<>();
        for (Integer a : categoryIdList) {
            typeList.add(typeService.findById(a));
        }
        movie.setTypes(typeList);
        movieService.updateMovie(file, movie);
        model.addAttribute("movieList", movieService.findAll());
        return "movieList";
    }

    @GetMapping("/showtime/{movieId}/schedule/{scheduleId}/cinema/{cinemaRoomId}/seats")
    public String showSeatSelection(@PathVariable UUID movieId,
                                    @PathVariable Integer scheduleId,
                                    @PathVariable UUID cinemaRoomId,
                                    Model model) {
        CinemaRoom cinemaRoom = cinemaRoomServices.findById(cinemaRoomId);
        List<Seat> seats = cinemaRoomServices.findAllSeatByCinema(cinemaRoom);
        Movie movie = movieService.findById(movieId);
        Schedule schedule = movieService.findScheduleById(scheduleId);
        List<ScheduleSeat> scheduleSeatList = new ArrayList<>();
        for (Seat seat: seats) {
            ScheduleSeat scheduleSeat = scheduleSeatService.findByMovieIdAndScheduleIdAndSeatIdAndCinemaId(movieId, scheduleId, seat.getId(), cinemaRoomId);
            if(scheduleSeat != null){
                scheduleSeatList.add(scheduleSeat);
            }else{
                scheduleSeat = scheduleSeatService.findByCinemaRoomAndSeat(cinemaRoomId,seat.getId());
                ScheduleSeat scheduleSeat1 = new ScheduleSeat();
                scheduleSeat1.setCinemaRoom(cinemaRoom);
                scheduleSeat1.setSeat(scheduleSeat.getSeat());
                scheduleSeat1.setSeatType(scheduleSeat.getSeatType());
                scheduleSeat1.setSeatStatus(scheduleSeat.getSeatStatus());
                scheduleSeat1.setMovie(movie);
                scheduleSeat1.setSchedule(schedule);

                scheduleSeatService.save(scheduleSeat1);
                scheduleSeatList.add(scheduleSeat1);
            }
        }
        model.addAttribute("movie", movie);
        model.addAttribute("scheduleSeatList", scheduleSeatList);
        model.addAttribute("schedule", schedule);
        model.addAttribute("seats", seats);
        model.addAttribute("cinemaRoom", cinemaRoom);
        return "selectSeat";
    }


    @PostMapping("/select-seat/{movieId}/schedule/{scheduleId}/cinema/{cinemaRoomId}/confirm-ticket")
    public String editSelectionSeat(Model model,
                                    @PathVariable UUID movieId,
                                    @PathVariable Integer scheduleId,
                                    @PathVariable UUID cinemaRoomId,
                                    @RequestParam("selected-seat") List<Integer> selectedSeat,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        CinemaRoom cinemaRoom = cinemaRoomServices.findById(cinemaRoomId);
        model.addAttribute("cinemaRoom",cinemaRoom);

        Schedule schedule = movieService.findScheduleById(scheduleId);
        model.addAttribute("schedule", schedule);

        List<ScheduleSeat> scheduleSeatList = new ArrayList<>();
        for (Integer seatId : selectedSeat) {
            ScheduleSeat scheduleSeat = scheduleSeatService.findByMovieIdAndScheduleIdAndSeatIdAndCinemaId(movieId, scheduleId, seatId, cinemaRoomId);
            scheduleSeat.setSeatStatus(SeatStatus.RESERVED);
//            scheduleSeatService.save(scheduleSeat);
            scheduleSeatList.add(scheduleSeat);
        }
        model.addAttribute("scheduleSeatList", scheduleSeatList);

        Movie movie = movieService.findById(movieId);
        Account account = customUserDetails.getAccount();
        model.addAttribute("movie", movie);
        model.addAttribute("account", account);
        model.addAttribute("date", LocalDate.now());
        return "userConfirmTicket";
    }


}
