package com.example.service;

import com.example.entities.Schedule;
import com.example.entities.ScheduleSeat;

import java.util.List;
import java.util.UUID;

public interface ScheduleService {
    Schedule save(Schedule schedule);

    Schedule findById(Integer id);

    List<Schedule> findAll();
}
