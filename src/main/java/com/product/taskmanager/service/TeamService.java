package com.product.taskmanager.service;

import com.product.taskmanager.dto.TeamDTO;
import com.product.taskmanager.model.Team;
import com.product.taskmanager.model.User;
import com.product.taskmanager.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public Team create(TeamDTO teamDTO){
        Team team = Team.builder()
                .name(teamDTO.getName())
                .build();

        return teamRepository.save(team);
    }

    public List<Team> readAll(){
        return teamRepository.findAll();
    }

    public Team update(Team team){
        return teamRepository.save(team);
    }

    public void delete(Long id){
        teamRepository.deleteById(id);
    }


}
