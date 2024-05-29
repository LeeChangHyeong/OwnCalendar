package org.example.owncalendarserver.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.owncalendarserver.dto.CommentRequestDto;
import org.example.owncalendarserver.dto.CommentResponseDto;
import org.example.owncalendarserver.entity.Comment;
import org.example.owncalendarserver.entity.Schedule;
import org.example.owncalendarserver.jwt.JwtUtil;
import org.example.owncalendarserver.repository.CommentRepository;
import org.example.owncalendarserver.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final JwtUtil jwtUtil;

    public CommentResponseDto createComment(CommentRequestDto requestDto, Long scheduleId, HttpServletRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );

        String token = jwtUtil.getJwtFromHeader(request);

        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰값을 확인해주세요.");
        }

        // 토큰으로 유저 정보 받아오기
        Claims user = jwtUtil.getUserInfoFromToken(token);

        Comment comment = new Comment(requestDto, schedule, user.getSubject());

        checkUser(comment.getUser_id(), request);

        Comment saveComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }

    @Transactional
    public CommentResponseDto editComment(CommentRequestDto requestDto, Long commentId, HttpServletRequest request) {
        Comment comment = checkCommentWithId(commentId);

        // 토큰으로 유저 정보 받아오기
        Claims user = getIdfromToken(request);

        checkUserId(comment, user.getSubject());

        checkUser(comment.getUser_id(), request);

        comment.update(requestDto);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

        return commentResponseDto;
    }

    // 토큰으로 아이디 들고옴
    private Claims getIdfromToken(HttpServletRequest request) {
        String token = jwtUtil.getJwtFromHeader(request);

        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰값을 확인해주세요.");
        }

        return jwtUtil.getUserInfoFromToken(token);
    }

    public void deleteComment(Long commentId, HttpServletRequest request) {
        Comment comment = checkCommentWithId(commentId);

        // 토큰으로 유저 정보 받아오기
        Claims user = getIdfromToken(request);

        checkUserId(comment, user.getSubject());

        checkUser(comment.getUser_id(), request);

        commentRepository.delete(comment);
    }

    private void checkUser(String id, HttpServletRequest request) {
        // 토큰으로 유저 정보 받아오기
        Claims user = getIdfromToken(request);

        if (!user.getSubject().equals(id)) {
            throw new IllegalArgumentException("스케쥴 작성자가 아니라 삭제가 불가능합니다.");
        }
    }

    // 있는 schedule인지 체크
    public Comment checkCommentWithId(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
        );
    }

    // 있는 comment인지 체크
    public void checkUserId(Comment comment, String userId) {
        if (!comment.getUser_id().equals(userId)) {
            throw new IllegalStateException("사용자가 일치하지 않습니다.");
        }
    }
}
