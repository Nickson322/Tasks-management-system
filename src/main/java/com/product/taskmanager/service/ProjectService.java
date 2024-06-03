package com.product.taskmanager.service;

import com.product.taskmanager.dto.project.ProjectReadDTO;
import com.product.taskmanager.dto.request.ProjectCreationRequest;
import com.product.taskmanager.dto.request.ProjectUpdateTeamRequest;
import com.product.taskmanager.dto.response.ProjectCreationResponse;
import com.product.taskmanager.dto.response.ProjectReadResponse;
import com.product.taskmanager.mapper.ProjectMapper;
import com.product.taskmanager.model.Project;
import com.product.taskmanager.model.Team;
import com.product.taskmanager.repository.ProjectRepository;
import com.product.taskmanager.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;

    public ProjectCreationResponse createProject(ProjectCreationRequest projectCreationRequest){
        Project project = ProjectMapper.INSTANCE.projectCreationRequestToProject(projectCreationRequest);

        project = projectRepository.save(project);

        return ProjectMapper.INSTANCE.projectToProjectCreationResponse(project);
    }

    public List<ProjectReadResponse> readAllProjects(){
        List<Project> projects = projectRepository.findAll();

        return ProjectMapper.INSTANCE.projectsToProjectReadResponse(projects);
    }

    //TODO: put leaderName

//    public ProjectReadDTO updateProjectTeam(ProjectUpdateTeamRequest projectUpdateTeamRequest,
//                                            Long id){
//        Project projecToUpdate = projectRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
//
//        Team team = teamRepository.findByName(projectUpdateTeamRequest.getTeamName());
//
//        if(team != null) projecToUpdate.setTeam(team);
//    }


}
