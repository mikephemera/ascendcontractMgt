package com.ascendcargo.contractmgt.repository;

import com.ascendcargo.contractmgt.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}