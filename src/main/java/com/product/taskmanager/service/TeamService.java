package com.product.taskmanager.service;

import com.product.taskmanager.dto.request.TeamCreationRequest;
import com.product.taskmanager.dto.request.TeamUpdateRequest;
import com.product.taskmanager.dto.response.TeamCreationResponse;
import com.product.taskmanager.dto.response.TeamReadResponse;
import com.product.taskmanager.mapper.TeamMapper;
import com.product.taskmanager.model.Team;
import com.product.taskmanager.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public List<TeamReadResponse> readAllTeams(){
        List<Team> teams = teamRepository.findAll();

        return TeamMapper.INSTANCE.teamsToTeamReadResponse(teams);
    }

    public String update(TeamUpdateRequest teamUpdateRequest, Long teamId){
        Team teamToUpdate = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: "
                        + teamId));

        teamToUpdate.setName(teamUpdateRequest.getName());
        teamToUpdate.setDescription(teamUpdateRequest.getDescription());

        teamRepository.save(teamToUpdate);

        return "Команда с id: " + teamId + " успешно обновлена";
    }

    public void delete(Long id){
        teamRepository.deleteById(id);
    }


}
