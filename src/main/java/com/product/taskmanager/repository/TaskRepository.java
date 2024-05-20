package com.product.taskmanager.repository;

import com.product.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByName(String name);
}
