package com.product.taskmanager.repository;

import com.product.taskmanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);

//    Team findBy(Long userId);
}
