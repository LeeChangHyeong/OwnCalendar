package org.example.owncalendarserver.service;

import org.example.owncalendarserver.dto.ScheduleRequestDto;
import org.example.owncalendarserver.dto.ScheduleResponseDto;
import org.example.owncalendarserver.entity.Schedule;
import org.example.owncalendarserver.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 생성자로 주입
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);

        return scheduleResponseDto;
    }
}
