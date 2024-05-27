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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "createDate", nullable = false)
    private Date createDate;
}
