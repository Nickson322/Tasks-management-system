package com.product.taskmanager.repository;

import com.product.taskmanager.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
