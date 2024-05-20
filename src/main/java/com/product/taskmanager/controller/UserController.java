package com.product.taskmanager.controller;

import com.product.taskmanager.dto.request.UserCreationRequest;
import com.product.taskmanager.dto.request.UserUpdateRequest;
import com.product.taskmanager.dto.request.UserUpdateTeamReq;
import com.product.taskmanager.dto.response.UserCreationResponse;
import com.product.taskmanager.dto.response.UserReadResponse;
import com.product.taskmanager.dto.user.*;
import com.product.taskmanager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreationResponse> createUser(@RequestBody UserCreationRequest userCreationRequest,
                                                           @RequestHeader Map<String, String> headers){
        return new ResponseEntity<>(userService.createUser(userCreationRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public UserReadResponse readUser(@PathVariable String name){
        return userService.readUser(name);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long id){
        userService.updateUser(userUpdateRequest, id);
    }

    @PutMapping("/{userId}/connect")
    public String updateUserTeam(@RequestBody UserUpdateTeamReq userUpdateTeamReq,
                                 @PathVariable Long userId){
        return userService.updateUserTeam(userUpdateTeamReq, userId);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        userService.delete(id);
        return HttpStatus.OK;
    }
}
