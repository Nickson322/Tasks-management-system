package com.product.taskmanager.mapper;

import com.product.taskmanager.dto.request.TaskCreationRequest;
import com.product.taskmanager.dto.request.TaskUpdateAuthorReq;
import com.product.taskmanager.dto.response.TaskCreationResponse;
import com.product.taskmanager.dto.response.TaskReadResponse;
import com.product.taskmanager.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task taskCreationRequestToTask(TaskCreationRequest taskCreationRequest);

    TaskCreationResponse taskToTaskCreationResponse(Task task);

    TaskReadResponse taskToTaskReadResponse(Task task);

    List<TaskReadResponse> tasksToTaskReadResponse(List<Task> tasks);
}
