package com.product.taskmanager.service;

import com.product.taskmanager.dto.ProjectCreateDTO;
import com.product.taskmanager.model.Project;
import com.product.taskmanager.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project create(ProjectCreateDTO projectCreateDTO){
        Project project = Project.builder()
        //

                .build();

        return projectRepository.save(project);
    }

    public List<Project> readAll(){
        return projectRepository.findAll();
    }

    public Project update(Project project){
        return projectRepository.save(project);
    }

    public void delete(String name){
        projectRepository.deleteByName(name);
    }
}
