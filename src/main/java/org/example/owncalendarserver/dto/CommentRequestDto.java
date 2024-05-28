package org.example.owncalendarserver.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;
    @NotEmpty(message = "user_id는 필수로 작성하셔야 합니다..")
    private String user_id;
}
