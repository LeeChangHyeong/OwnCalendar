package org.example.owncalendarserver.repository;

import org.example.owncalendarserver.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByCreateDateDesc();
}
