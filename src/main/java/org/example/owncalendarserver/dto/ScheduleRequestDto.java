package org.example.owncalendarserver.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    @NotEmpty(message = "title은 꼭 입력하셔야 합니다.")
    private String title;
    @NotEmpty(message = "content는 꼭 입력하셔야 합니다.")
    private String content;
    @NotEmpty(message = "password는 꼭 입력하셔야 합니다.")
    private String password;
}
