package com.product.taskmanager.controller;

import com.product.taskmanager.dto.request.SprintCreationRequest;
import com.product.taskmanager.dto.response.SprintCreationResponse;
import com.product.taskmanager.dto.response.SprintsReadResponse;
import com.product.taskmanager.service.SprintService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/sprints")
public class SprintController {
    private final SprintService sprintService;

    @PostMapping
    public SprintCreationResponse createSprint(@RequestBody SprintCreationRequest sprintCreationRequest){
        return sprintService.createSprint(sprintCreationRequest);
    }

    //TODO: сделать получение спринтов по айди авторизованного пользователя
    @GetMapping
    public SprintsReadResponse readSprints(@RequestBody Long userId){
        return null;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        sprintService.delete(id);
        return HttpStatus.OK;
    }
}
