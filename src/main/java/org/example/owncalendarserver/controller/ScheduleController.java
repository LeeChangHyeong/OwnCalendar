package org.example.owncalendarserver.controller;

import org.example.owncalendarserver.dto.ScheduleRequestDto;
import org.example.owncalendarserver.dto.ScheduleResponseDto;
import org.example.owncalendarserver.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 스케쥴 저장
    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // 선택 스케쥴 조회
    @GetMapping("/schedule/id")
    public ScheduleResponseDto getSelectSchedule(Long id) {
        return scheduleService.getSelectSchedule(id);
    }
}
