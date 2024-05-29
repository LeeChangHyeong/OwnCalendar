package org.example.owncalendarserver.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;
}
