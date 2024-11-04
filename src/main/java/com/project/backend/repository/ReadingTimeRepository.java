package com.project.backend.repository;

import com.project.backend.entity.ReadingTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingTimeRepository extends JpaRepository<ReadingTime, Long> {
}
