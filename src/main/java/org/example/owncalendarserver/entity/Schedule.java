package org.example.owncalendarserver.entity;

import io.jsonwebtoken.Claims;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.owncalendarserver.dto.ScheduleRequestDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "schedule") // 테이블 이름 지정
@NoArgsConstructor // 파라미터가 없는 디폴트 생성사 생성
public class Schedule {
    @Id
    // 기본키 생성을 DB에 위임
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "createDate", nullable = false)
    private Date createDate;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Schedule(ScheduleRequestDto requestDto, String userName, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.name = userName;
        this.createDate = new Date();
        this.comments = new ArrayList<>();
        this.user = user;
    }

    public void update(ScheduleRequestDto scheduleRequestDto) {
        this.title = scheduleRequestDto.getTitle();
        this.content = scheduleRequestDto.getContent();
    }
}
