package com.product.taskmanager.service;

import com.product.taskmanager.dto.UserCreateDTO;
import com.product.taskmanager.dto.UserUpdateDTO;
import com.product.taskmanager.model.Team;
import com.product.taskmanager.model.User;
import com.product.taskmanager.repository.TeamRepository;
import com.product.taskmanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public User create(UserCreateDTO userCreateDTO){
        User user = User.builder()
                .name(userCreateDTO.getName())
                .password(userCreateDTO.getPassword())
                .build();

        return userRepository.save(user);
    }

    public List<User> readAll(){
        return userRepository.findAll();
    }

    public User readUser(String name){
        return userRepository.findByName(name).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    public User update(UserUpdateDTO userUpdateDTO, Long id){
        User userToUpdate = userRepository.findById(id).orElse(null);
        if(userToUpdate != null){
            userToUpdate.setName(userUpdateDTO.getName());
            userToUpdate.setUserRole(userUpdateDTO.getUserRole());
            userToUpdate.setEmail(userUpdateDTO.getEmail());
            userToUpdate.setPassword(userUpdateDTO.getPassword());

            Team team = teamRepository.findByName(userUpdateDTO.getTeamName());
            if (team != null) userToUpdate.setTeam(team);

            return userRepository.save(userToUpdate);
        }

        return null;
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
