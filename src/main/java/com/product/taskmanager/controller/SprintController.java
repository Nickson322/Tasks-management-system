package com.product.taskmanager.controller;

import com.product.taskmanager.dto.SprintCreateDTO;
import com.product.taskmanager.model.Sprint;
import com.product.taskmanager.service.SprintService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sprints")
public class SprintController {
    private final SprintService sprintService;

    @PostMapping
    public ResponseEntity<Sprint> create(@RequestBody SprintCreateDTO sprintCreateDTO){
        return new ResponseEntity<>(sprintService.create(sprintCreateDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Sprint>> readAll(){
        return new ResponseEntity<>(sprintService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Sprint> update(@RequestBody Sprint sprint){
        return new ResponseEntity<>(sprintService.update(sprint), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        sprintService.delete(id);
        return HttpStatus.OK;
    }
}
