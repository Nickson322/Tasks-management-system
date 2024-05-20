package com.product.taskmanager.mapper;

import com.product.taskmanager.dto.request.SprintCreationRequest;
import com.product.taskmanager.dto.response.SprintCreationResponse;
import com.product.taskmanager.model.Sprint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SprintMapper {
    SprintMapper INSTANCE = Mappers.getMapper(SprintMapper.class);

    Sprint sprintCreationRequestToSprint(SprintCreationRequest sprintCreationRequest);
    SprintCreationResponse sprintToSprintCreationResponse(Sprint sprint);
}
