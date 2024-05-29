package org.example.owncalendarserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.owncalendarserver.dto.CommentRequestDto;

import java.util.Date;

@Entity
@Getter
@Table
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "createDate", nullable = false)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment(CommentRequestDto requestDto, Schedule schedule, String user_id) {
        this.content = requestDto.getContent();
        this.user_id = user_id;
        this.createDate = new Date();
        this.schedule = schedule;
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
