package com.product.taskmanager.service;

import com.product.taskmanager.dto.request.*;
import com.product.taskmanager.dto.response.TaskCreationResponse;
import com.product.taskmanager.dto.response.TaskReadResponse;
import com.product.taskmanager.mapper.TaskMapper;
import com.product.taskmanager.model.Task;
import com.product.taskmanager.model.User;
import com.product.taskmanager.repository.TaskRepository;
import com.product.taskmanager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskCreationResponse createTask(TaskCreationRequest taskCreationRequest){
        Task task = TaskMapper.INSTANCE.taskCreationRequestToTask(taskCreationRequest);

        User author = userRepository.findByName(taskCreationRequest.getAuthorName());
        task.setAuthor(author);

        task = taskRepository.save(task);

        return TaskMapper.INSTANCE.taskToTaskCreationResponse(task);
    }

    public List<TaskReadResponse> readAll(){
        List<Task> tasks = taskRepository.findAll();

        return TaskMapper.INSTANCE.tasksToTaskReadResponse(tasks);
    }

    public TaskReadResponse readTask(Long taskId){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Задача не найдена"));

        return TaskMapper.INSTANCE.taskToTaskReadResponse(task);
    }

    public String update(Task task){
        taskRepository.save(task);
        return "Задача с id: " + task.getId() + "обновлена";
    }

    public String updateTaskStatus(TaskUpdateStatusReq taskUpdateStatusReq, Long taskId){
        Task taskToUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: "
                                                                + taskId));

        if(taskUpdateStatusReq.getStatus() != null)
            taskToUpdate.setStatus(taskUpdateStatusReq.getStatus());

        taskRepository.save(taskToUpdate);

        return "Статус задачи с id: " + taskId + " успешно обновлён";
    }

    public String updateTaskType(TaskUpdateTypeReq taskUpdateTypeReq, Long taskId){
        Task taskToUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: "
                        + taskId));

        if(taskUpdateTypeReq.getType() != null)
            taskToUpdate.setType(taskUpdateTypeReq.getType());

        taskRepository.save(taskToUpdate);

        return "Тип задачи с id: " + taskId + " успешно обновлён";
    }

    public String updateTaskTimeSpent(TaskUpdateTimeSpentReq taskUpdateTimeSpentReq, Long taskId){
        Task taskToUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: "
                        + taskId));

        if(taskUpdateTimeSpentReq.getTimeSpent() != null)
            taskToUpdate.setTimeSpent(taskUpdateTimeSpentReq.getTimeSpent());

        taskRepository.save(taskToUpdate);

        return "Время, проведённое над задачей с id: " + taskId + "успешно обновлено";
    }

    public String updateTaskPriority(TaskUpdatePriorityReq taskUpdatePriorityReq, Long taskId){
        Task taskToUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: "
                        + taskId));

        if(taskUpdatePriorityReq.getPriority() != null)
            taskToUpdate.setTimeSpent(taskUpdatePriorityReq.getPriority());

        taskRepository.save(taskToUpdate);

        return "Приоритет задачи с id: " + taskId + "успешно изменён";
    }

    public void delete(Long id){
        taskRepository.deleteById(id);
    }
}