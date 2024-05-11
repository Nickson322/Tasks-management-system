package com.product.taskmanager.controller;

import com.product.taskmanager.dto.task.TaskCreateDTO;
import com.product.taskmanager.model.Task;
import com.product.taskmanager.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskCreateDTO taskCreateDTO){
        return new ResponseEntity<>(taskService.create(taskCreateDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Task>> readAll(){
        return new ResponseEntity<>(taskService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Task> update(@RequestBody Task task){
        return new ResponseEntity<>(taskService.update(task), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        taskService.delete(id);
        return HttpStatus.OK;
    }
}
