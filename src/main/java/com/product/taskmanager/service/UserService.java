package com.product.taskmanager.service;

import com.product.taskmanager.dto.request.UserCreationRequest;
import com.product.taskmanager.dto.request.UserUpdateRequest;
import com.product.taskmanager.dto.request.UserUpdateTeamReq;
import com.product.taskmanager.dto.response.UserCreationResponse;
import com.product.taskmanager.dto.response.UserReadResponse;
import com.product.taskmanager.mapper.UserMapper;
import com.product.taskmanager.model.Team;
import com.product.taskmanager.model.User;
import com.product.taskmanager.repository.TeamRepository;
import com.product.taskmanager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public UserCreationResponse createUser(UserCreationRequest userCreationRequest){
        User user = UserMapper.INSTANCE.userCreationRequestToUser(userCreationRequest);

        user = userRepository.save(user);

        return UserMapper.INSTANCE.userToUserCreationResponse(user);
    }

    public UserReadResponse readUser(String name){
        User user = userRepository.findByName(name);

        return UserMapper.INSTANCE.userToUserReadResponse(user);
    }

    public void updateUser(UserUpdateRequest userUpdateRequest, Long id){
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        userToUpdate = UserMapper.INSTANCE.updateUserFromUpdateRequest(userUpdateRequest, userToUpdate);

        userRepository.save(userToUpdate);
    }

    public String updateUserTeam(UserUpdateTeamReq userUpdateTeamReq, Long id){
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        Team team = teamRepository.findByName(userUpdateTeamReq.getTeamName());

        if(team != null) userToUpdate.setTeam(team);

        userToUpdate = userRepository.save(userToUpdate); //TODO: СРОЧНО ПРЕОБРАЗОВАТЬ В DTO

        return "User connected to " + userUpdateTeamReq.getTeamName() + " team!";
    }




    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
