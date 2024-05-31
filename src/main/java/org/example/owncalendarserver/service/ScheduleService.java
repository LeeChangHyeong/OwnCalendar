package org.example.owncalendarserver.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.owncalendarserver.dto.ScheduleRequestDto;
import org.example.owncalendarserver.dto.ScheduleResponseDto;
import org.example.owncalendarserver.entity.Schedule;
import org.example.owncalendarserver.entity.User;
import org.example.owncalendarserver.jwt.JwtUtil;
import org.example.owncalendarserver.repository.ScheduleRepository;
import org.example.owncalendarserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // final 키워드를 가진 모든 것에 대한 생성자를 만들어준다.
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 스케쥴 생성
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, HttpServletRequest request) {

        // 토큰으로 유저 정보 받아오기
        Claims user = getIdfromToken(request);

        User realUser = userRepository.findByUserName(user.getSubject()).orElseThrow(() ->
                new IllegalArgumentException("유저를 찾을 수 없습니다.")
        );

        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto, user.getSubject(), realUser);

        checkUser(user.getSubject(), request);

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
    public ScheduleResponseDto editSchedule(Long id, ScheduleRequestDto requestDto, HttpServletRequest request) {
        // 해당 메모가 DB에 존재하는지 확인
        // 옵셔널 해제
        Schedule schedule = findSchedule(id);

        // 토큰으로 유저 정보 받아오기
        Claims user = getIdfromToken(request);

        // 작성한 유저 확인
        checkUser(user.getSubject(), request);

        // 메모 내용 수정
        schedule.update(requestDto);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public void deleteSchedule(Long id, HttpServletRequest request) {
        // 해당 스케쥴이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        // 토큰으로 유저 정보 받아오기
        Claims user = getIdfromToken(request);

        // 작성한 유저 확인
        checkUser(user.getSubject(), request);

        // 스케쥴 삭제
        scheduleRepository.delete(schedule);

        return;
    }

    private void checkUser(String id, HttpServletRequest request) {
        // 토큰 확인
        String token = jwtUtil.getJwtFromHeader(request);

        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰값을 확인해주세요.");
        }

        // 토큰으로 유저 정보 받아오기
        Claims user = jwtUtil.getUserInfoFromToken(token);
        System.out.println(user.getSubject());

        if (!user.getSubject().equals(id)) {
            throw new IllegalArgumentException("스케쥴 작성자가 아니라 삭제가 불가능합니다.");
        }
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
    // 토큰으로 아이디 들고옴
    private Claims getIdfromToken(HttpServletRequest request) {
        String token = jwtUtil.getJwtFromHeader(request);

        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰값을 확인해주세요.");
        }

        return jwtUtil.getUserInfoFromToken(token);
    }
}
