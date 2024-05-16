package org.example.owncalendarserver.repository;

import org.example.owncalendarserver.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
