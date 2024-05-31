package org.example.owncalendarserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.owncalendarserver.dto.ScheduleRequestDto;
import org.example.owncalendarserver.dto.ScheduleResponseDto;
import org.example.owncalendarserver.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 스케쥴 저장
    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody @Valid ScheduleRequestDto requestDto, HttpServletRequest request) {
        return scheduleService.createSchedule(requestDto, request);
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
    public ScheduleResponseDto editSchedule(@PathVariable Long id, @RequestBody @Valid ScheduleRequestDto requestDto, HttpServletRequest request) {
        return scheduleService.editSchedule(id, requestDto, request);
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Map<String, Object>> deleteSchedule(@PathVariable Long id, HttpServletRequest request) {
        scheduleService.deleteSchedule(id, request);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "삭제가 성공적으로 이루어졌습니다.");
        response.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
