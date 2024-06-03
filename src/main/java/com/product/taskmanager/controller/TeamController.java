package com.product.taskmanager.controller;

import com.product.taskmanager.dto.request.TeamCreationRequest;
import com.product.taskmanager.dto.request.TeamUpdateRequest;
import com.product.taskmanager.dto.response.TeamCreationResponse;
import com.product.taskmanager.dto.response.TeamReadResponse;
import com.product.taskmanager.model.Team;
import com.product.taskmanager.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/teams")
@CrossOrigin(origins = "http://localhost:5173")
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    public TeamCreationResponse create(@RequestBody TeamCreationRequest teamCreationRequest){
        return teamService.createTeam(teamCreationRequest);
    }

    @GetMapping("/{name}")
    public TeamReadResponse readTeam(@PathVariable String name){
        return teamService.readTeam(name);
    }

    @GetMapping
    public List<TeamReadResponse> readAllTeams(){
        return teamService.readAllTeams();
    }

    @PutMapping("/{teamId}")
    public String update(@RequestBody TeamUpdateRequest teamUpdateRequest,
                                   @PathVariable Long teamId){
        return teamService.update(teamUpdateRequest, teamId);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        teamService.delete(id);
        return HttpStatus.OK;
    }
}
