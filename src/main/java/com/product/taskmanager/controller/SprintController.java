package com.product.taskmanager.controller;

import com.product.taskmanager.dto.request.SprintCreationRequest;
import com.product.taskmanager.dto.response.SprintCreationResponse;
import com.product.taskmanager.dto.response.SprintReadResponse;
import com.product.taskmanager.service.SprintService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sprints")
@CrossOrigin(origins = "http://localhost:5173")
public class SprintController {
    private final SprintService sprintService;

    @PostMapping
    public SprintCreationResponse createSprint(@RequestBody SprintCreationRequest sprintCreationRequest){
        return sprintService.createSprint(sprintCreationRequest);
    }

    //TODO: сделать получение спринтов по айди авторизованного пользователя
    @GetMapping
    public List<SprintReadResponse> readSprints(){
//        return sprintService.readUserSprints();
        return null;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        sprintService.delete(id);
        return HttpStatus.OK;
    }
}
