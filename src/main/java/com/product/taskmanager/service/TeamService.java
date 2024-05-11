package com.product.taskmanager.service;

import com.product.taskmanager.dto.request.TeamCreationRequest;
import com.product.taskmanager.dto.response.TeamCreationResponse;
import com.product.taskmanager.dto.response.TeamReadResponse;
import com.product.taskmanager.mapper.TeamMapper;
import com.product.taskmanager.model.Team;
import com.product.taskmanager.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamCreationResponse createTeam(TeamCreationRequest teamCreationRequest){
        Team team = TeamMapper.INSTANCE.teamCreationRequestToTeam(teamCreationRequest);

        team = teamRepository.save(team);

        return TeamMapper.INSTANCE.teamToTeamCreationResponse(team);
    }

    public TeamReadResponse readTeam(String name){
        Team team = teamRepository.findByName(name);

        return TeamMapper.INSTANCE.teamToTeamReadResponse(team);
    }

    public List<Team> readAll(){
        return teamRepository.findAll();
    }

    public Team getTeam(String name){
        return teamRepository.findByName(name);
    }

    public Team update(Team team){
        return teamRepository.save(team);
    }

    public void delete(Long id){
        teamRepository.deleteById(id);
    }


}
