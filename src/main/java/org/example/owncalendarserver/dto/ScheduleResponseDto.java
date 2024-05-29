package org.example.owncalendarserver.dto;

import lombok.Getter;
import org.example.owncalendarserver.entity.Schedule;

import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private Date createDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createDate = schedule.getCreateDate();
    }
}
