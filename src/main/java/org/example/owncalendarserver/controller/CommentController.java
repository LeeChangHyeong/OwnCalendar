package org.example.owncalendarserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.owncalendarserver.dto.CommentRequestDto;
import org.example.owncalendarserver.dto.CommentResponseDto;
import org.example.owncalendarserver.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{schedule_id}")
    public CommentResponseDto createComment(@PathVariable Long schedule_id, @RequestBody @Valid CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.createComment(requestDto, schedule_id, request);
    }

    @PutMapping("/comment/{comment_id}")
    public CommentResponseDto editComment(@PathVariable Long comment_id, @RequestBody @Valid CommentRequestDto requestDto, HttpServletRequest request) {
            return commentService.editComment(requestDto, comment_id, request);
    }

    @DeleteMapping("/comment/{comment_id}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable Long comment_id, HttpServletRequest request) {
        commentService.deleteComment(comment_id, request);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "삭제가 성공적으로 이루어졌습니다.");
        response.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
