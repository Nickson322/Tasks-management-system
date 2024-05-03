package com.product.taskmanager.controller;

import com.product.taskmanager.dto.UserCreateDTO;
import com.product.taskmanager.dto.UserUpdateDTO;
import com.product.taskmanager.model.User;
import com.product.taskmanager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserCreateDTO userCreateDTO){
        return new ResponseEntity<>(userService.create(userCreateDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> readAll(){
        return new ResponseEntity<>(userService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> readUser(@PathVariable String name){
        return new ResponseEntity<>(userService.readUser(name), HttpStatus.OK);
    }

    //TODO user update in UserController
    @PutMapping("/{id}")
    public void update(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Long id){
        userService.update(userUpdateDTO, id);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        userService.delete(id);
        return HttpStatus.OK;
    }
}
