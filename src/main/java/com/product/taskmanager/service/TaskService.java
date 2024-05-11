package com.product.taskmanager.service;

import com.product.taskmanager.dto.task.TaskCreateDTO;
import com.product.taskmanager.model.Task;
import com.product.taskmanager.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task create(TaskCreateDTO taskCreateDTO){
        Task task = Task.builder()
        //

                .build();

        return taskRepository.save(task);
    }

    public List<Task> readAll(){
        return taskRepository.findAll();
    }

    public Task readTask(String name){
        return taskRepository.findByName(name).orElseThrow(() -> new RuntimeException("Задача не найдена"));
    }

    public Task update(Task task){
        return taskRepository.save(task);
    }

    public void delete(Long id){
        taskRepository.deleteById(id);
    }
}