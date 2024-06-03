package com.product.taskmanager.controller;

import com.product.taskmanager.dto.request.*;
import com.product.taskmanager.dto.response.TaskCreationResponse;
import com.product.taskmanager.dto.response.TaskReadResponse;
import com.product.taskmanager.model.Task;
import com.product.taskmanager.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public TaskCreationResponse create(@RequestBody TaskCreationRequest taskCreationRequest){
        return taskService.createTask(taskCreationRequest);
    }

    @GetMapping("/{taskId}")
    public TaskReadResponse readTask(@PathVariable Long taskId){
        return taskService.readTask(taskId);
    }

    @GetMapping
    public List<TaskReadResponse> readAll(){
        return taskService.readAll();
    }

    @PutMapping
    public String update(@RequestBody Task task){
        return taskService.update(task);
    }

    @PutMapping("/{taskId}/status")
    public String updateTaskStatus(@RequestBody TaskUpdateStatusReq taskUpdateStatusReq,
                                   @PathVariable Long taskId){
        return taskService.updateTaskStatus(taskUpdateStatusReq, taskId);
    }

    @PutMapping("/{taskId}/type")
    public String updateTaskType(@RequestBody TaskUpdateTypeReq taskUpdateTypeReq,
                                 @PathVariable Long taskId){
        return taskService.updateTaskType(taskUpdateTypeReq, taskId);
    }

    @PutMapping("/{taskId}/timeSpent")
    public String updateTaskTimeSpent(@RequestBody TaskUpdateTimeSpentReq taskUpdateTimeSpentReq,
                                      @PathVariable Long taskId){
        return taskService.updateTaskTimeSpent(taskUpdateTimeSpentReq, taskId);
    }

    @PutMapping("/{taskId}/priority")
    public String updateTaskPriority(@RequestBody TaskUpdatePriorityReq taskUpdatePriorityReq,
                                     @PathVariable Long taskId){
        return taskService.updateTaskPriority(taskUpdatePriorityReq, taskId);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        taskService.delete(id);
        return HttpStatus.OK;
    }
}
