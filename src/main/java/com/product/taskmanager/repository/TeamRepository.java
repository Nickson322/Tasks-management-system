package com.product.taskmanager.repository;

import com.product.taskmanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
