package com.product.taskmanager.controller;

import com.product.taskmanager.dto.project.ProjectReadDTO;
import com.product.taskmanager.dto.request.ProjectCreationRequest;
import com.product.taskmanager.dto.request.ProjectUpdateTeamRequest;
import com.product.taskmanager.dto.response.ProjectCreationResponse;
import com.product.taskmanager.dto.response.ProjectReadResponse;
import com.product.taskmanager.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/projects")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ProjectCreationResponse create(@RequestBody ProjectCreationRequest projectCreationRequest){
        return projectService.createProject(projectCreationRequest);
    }

    @GetMapping
    public List<ProjectReadResponse> readProjects(){
        return projectService.readAllProjects();
    }


//    @PutMapping("/{id}")
//    public ProjectReadDTO updateProjectTeam(@RequestBody ProjectUpdateTeamRequest projectUpdateTeamRequest,
//                                            @PathVariable Long id){
//        projectService.updateProjectTeam(projectUpdateTeamRequest, id);
//    }
}
