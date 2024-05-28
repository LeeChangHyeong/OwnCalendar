package org.example.owncalendarserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.owncalendarserver.dto.CommentRequestDto;
import org.example.owncalendarserver.dto.CommentResponseDto;
import org.example.owncalendarserver.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{schedule_id}")
    public CommentResponseDto createComment(@PathVariable Long schedule_id, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto, schedule_id);
    }

    @PostMapping("/editComment/{comment_id}")
    public CommentResponseDto editComment(@PathVariable Long comment_id, @RequestBody CommentRequestDto requestDto) {
            return commentService.editComment(requestDto, comment_id);
    }
}
