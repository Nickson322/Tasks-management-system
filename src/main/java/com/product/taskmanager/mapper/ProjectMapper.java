package com.product.taskmanager.mapper;

import com.product.taskmanager.dto.request.ProjectCreationRequest;
import com.product.taskmanager.dto.response.ProjectCreationResponse;
import com.product.taskmanager.dto.response.ProjectReadResponse;
import com.product.taskmanager.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    Project projectCreationRequestToProject(ProjectCreationRequest projectCreationRequest);

    ProjectCreationResponse projectToProjectCreationResponse(Project project);

    List<ProjectReadResponse> projectsToProjectReadResponse(List<Project> projects);
}
