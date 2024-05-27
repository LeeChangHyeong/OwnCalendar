package org.example.owncalendarserver.service;

import lombok.RequiredArgsConstructor;
import org.example.owncalendarserver.dto.CommentRequestDto;
import org.example.owncalendarserver.dto.CommentResponseDto;
import org.example.owncalendarserver.entity.Comment;
import org.example.owncalendarserver.entity.Schedule;
import org.example.owncalendarserver.repository.CommentRepository;
import org.example.owncalendarserver.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto, Long schedule_id) {
        Schedule schedule = scheduleRepository.findById(schedule_id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );

        Comment comment = new Comment(requestDto, schedule);

        Comment saveComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }
}
