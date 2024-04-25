package com.product.taskmanager.controller;

import com.product.taskmanager.dto.TeamDTO;
import com.product.taskmanager.model.Team;
import com.product.taskmanager.model.User;
import com.product.taskmanager.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    //post
    @PostMapping
    public ResponseEntity<Team> create(@RequestBody TeamDTO teamDTO){
        return new ResponseEntity<>(teamService.create(teamDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Team>> readAll(){
        return new ResponseEntity<>(teamService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Team> update(@RequestBody Team team){
        return new ResponseEntity<>(teamService.update(team), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        teamService.delete(id);
        return HttpStatus.OK;
    }

}
