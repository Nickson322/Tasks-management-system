package com.product.taskmanager.service;

import com.product.taskmanager.dto.request.SprintCreationRequest;
import com.product.taskmanager.dto.response.SprintCreationResponse;
import com.product.taskmanager.dto.response.SprintReadResponse;
import com.product.taskmanager.mapper.SprintMapper;
import com.product.taskmanager.model.Sprint;
import com.product.taskmanager.model.Team;
import com.product.taskmanager.repository.SprintRepository;
import com.product.taskmanager.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SprintService {
    private final SprintRepository sprintRepository;
    private final TeamRepository teamRepository;

    public SprintCreationResponse createSprint(SprintCreationRequest sprintCreationRequest){
        Sprint sprint = SprintMapper.INSTANCE.sprintCreationRequestToSprint(sprintCreationRequest);

        sprint = sprintRepository.save(sprint);

        return SprintMapper.INSTANCE.sprintToSprintCreationResponse(sprint);
    }


    public void delete(Long id){
        sprintRepository.deleteById(id);
    }
}
