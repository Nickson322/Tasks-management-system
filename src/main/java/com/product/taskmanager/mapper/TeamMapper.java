package com.product.taskmanager.mapper;

import com.product.taskmanager.dto.request.TeamCreationRequest;
import com.product.taskmanager.dto.response.TeamCreationResponse;
import com.product.taskmanager.dto.response.TeamReadResponse;
import com.product.taskmanager.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    Team teamCreationRequestToTeam(TeamCreationRequest teamCreationRequest);

    TeamCreationResponse teamToTeamCreationResponse(Team team);


    TeamReadResponse teamToTeamReadResponse(Team team);

    List<TeamReadResponse> teamsToTeamReadResponse(List<Team> teams);
}
