package com.product.taskmanager.service;

import com.product.taskmanager.dto.request.UserCreationRequest;
import com.product.taskmanager.dto.request.UserUpdateRequest;
import com.product.taskmanager.dto.request.UserUpdateTeamReq;
import com.product.taskmanager.dto.response.UserCreationResponse;
import com.product.taskmanager.dto.response.UserReadResponse;
import com.product.taskmanager.dto.sprint.SprintReadDTO;
import com.product.taskmanager.mapper.SprintMapper;
import com.product.taskmanager.mapper.UserMapper;
import com.product.taskmanager.model.Sprint;
import com.product.taskmanager.model.Team;
import com.product.taskmanager.model.User;
import com.product.taskmanager.repository.SprintRepository;
import com.product.taskmanager.repository.TeamRepository;
import com.product.taskmanager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final SprintRepository sprintRepository;

    public UserCreationResponse createUser(UserCreationRequest userCreationRequest){
        User user = UserMapper.INSTANCE.userCreationRequestToUser(userCreationRequest);

        user = userRepository.save(user);

        return UserMapper.INSTANCE.userToUserCreationResponse(user);
    }

    public UserReadResponse readUser(String name){
        User user = userRepository.findByName(name);

        return UserMapper.INSTANCE.userToUserReadResponse(user);
    }

    public List<UserReadResponse> readAllUsers(){
        List<User> users = userRepository.findAll();

        return UserMapper.INSTANCE.usersToUserReadResponse(users);
    }

    public List<SprintReadDTO> readSprintsByUserId(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            Long teamId = user.get().getTeam().getId();
            List<Sprint> sprints = sprintRepository.findByTeamId(teamId);
            return SprintMapper.INSTANCE.toSprintReadDTOs(sprints);
        } else {
            throw new RuntimeException("User not found");
        }
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

        userRepository.save(userToUpdate); //TODO: СРОЧНО ПРЕОБРАЗОВАТЬ В DTO

        return "User connected to " + userUpdateTeamReq.getTeamName() + " team!";
    }




    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
