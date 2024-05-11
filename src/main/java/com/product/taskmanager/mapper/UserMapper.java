package com.product.taskmanager.mapper;

import com.product.taskmanager.dto.request.UserCreationRequest;
import com.product.taskmanager.dto.request.UserUpdateRequest;
import com.product.taskmanager.dto.response.UserCreationResponse;
import com.product.taskmanager.dto.response.UserReadResponse;
import com.product.taskmanager.dto.response.UserUpdateResponse;
import com.product.taskmanager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userCreationRequestToUser(UserCreationRequest userCreationRequest);

    UserCreationResponse userToUserCreationResponse(User user);


    UserReadResponse userToUserReadResponse(User user);


    @Mapping(target = "id", ignore = true)
    User updateUserFromUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);


}
