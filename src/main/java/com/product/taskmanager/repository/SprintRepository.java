package com.product.taskmanager.repository;

import com.product.taskmanager.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
    List<Sprint> findByTeamName(String teamName);

    List<Sprint> findByTeamId(Long teamId);
}
