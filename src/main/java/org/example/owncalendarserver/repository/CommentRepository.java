package org.example.owncalendarserver.repository;

import org.example.owncalendarserver.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
