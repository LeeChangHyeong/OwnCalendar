package org.example.owncalendarserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.owncalendarserver.dto.ScheduleRequestDto;
import org.example.owncalendarserver.dto.ScheduleResponseDto;
import org.example.owncalendarserver.entity.Schedule;
import org.example.owncalendarserver.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // final 키워드를 가진 모든 것에 대한 생성자를 만들어준다.
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // 스케쥴 생성
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);

        return scheduleResponseDto;
    }

    // 특정 스케쥴 조회
    public ScheduleResponseDto getSelectSchedule(Long id) {
        // 해당 스케쥴이 DB에 존재하는지 확인
        return scheduleRepository.findById(id)
                .map(ScheduleResponseDto::new)
                .orElseThrow(() ->
                        new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
                );
    }

    // 전체 스케쥴 조회
    public List<ScheduleResponseDto> getSchedule() {
        return scheduleRepository.findAllByOrderByCreateDateDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    // 특정 스케쥴 수정
    @Transactional
    public ScheduleResponseDto editSchedule(Long id, ScheduleRequestDto requestDto, String password) {
        // 해당 메모가 DB에 존재하는지 확인
        // 옵셔널 해제
        Schedule schedule = findSchedule(id);

        // 비밀번호 확인
        checkPassword(schedule, password);

        // 메모 내용 수정
        schedule.update(requestDto);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public Long deleteSchedule(Long id, String password) {
        // 해당 스케쥴이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        // 비밀번호 확인
        checkPassword(schedule, password);

        // 스케쥴 삭제
        scheduleRepository.delete(schedule);

        return id;
    }

    // 비밀번호 확인
    public void checkPassword(Schedule schedule, String password) {
        if(!schedule.getPassword().equals(password)){
            throw new IllegalStateException("비밀번호가 틀립니다.");
        }
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
