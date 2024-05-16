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

    public ScheduleResponseDto getSelectSchedule(Long id) {
        // 해당 스케쥴이 DB에 존재하는지 확인
        return scheduleRepository.findById(id)
                .map(ScheduleResponseDto::new)
                .orElseThrow(() ->
                        new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
                );
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
