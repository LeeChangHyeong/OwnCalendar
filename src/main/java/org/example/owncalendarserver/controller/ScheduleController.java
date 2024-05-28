package org.example.owncalendarserver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.owncalendarserver.dto.ScheduleRequestDto;
import org.example.owncalendarserver.dto.ScheduleResponseDto;
import org.example.owncalendarserver.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 스케쥴 저장
    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody @Valid ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // 선택 스케쥴 조회
    @GetMapping("/schedule/id")
    public ScheduleResponseDto getSelectSchedule(Long id) {
        return scheduleService.getSelectSchedule(id);
    }

    // 모든 스케쥴 조회
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedule() {
        return scheduleService.getSchedule();
    }

    // 특정 스케쥴 수정
    @PutMapping("/schedule/{id}")
    public ScheduleResponseDto editSchedule(@PathVariable Long id, @RequestBody @Valid ScheduleRequestDto requestDto) {
        return scheduleService.editSchedule(id, requestDto, requestDto.getPassword());
    }

    @DeleteMapping("/schedule/{id}")
    public Long deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.deleteSchedule(id, requestDto.getPassword());
    }
}
