package com.product.taskmanager.controller;

import com.product.taskmanager.dto.ProjectCreateDTO;
import com.product.taskmanager.model.Project;
import com.product.taskmanager.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody ProjectCreateDTO projectCreateDTO){
        return new ResponseEntity<>(projectService.create(projectCreateDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Project>> readAll(){
        return new ResponseEntity<>(projectService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Project> update(@RequestBody Project project){
        return new ResponseEntity<>(projectService.update(project), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public HttpStatus delete(@PathVariable String name){
        projectService.delete(name);
        return HttpStatus.OK;
    }
}
