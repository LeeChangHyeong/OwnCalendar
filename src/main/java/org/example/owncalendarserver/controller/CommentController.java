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
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long schedule_id) {
        return commentService.createComment(requestDto, schedule_id);
    }
}
