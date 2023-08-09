package com.example.service.impl;

import com.example.entities.Schedule;
import com.example.repository.ScheduleRepository;
import com.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule findById(Integer id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }
}
