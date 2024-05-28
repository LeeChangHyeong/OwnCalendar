package org.example.owncalendarserver.dto;

import lombok.Getter;
import org.example.owncalendarserver.entity.Comment;

import java.util.Date;
@Getter
public class CommentResponseDto {
    private String content;
    private String user_id;
    private Date creatDate;


    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.user_id = comment.getUser_id();
        this.creatDate = comment.getCreateDate();
    }
}