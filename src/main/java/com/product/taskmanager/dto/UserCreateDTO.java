package com.product.taskmanager.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String name;
    private String login;
    private String password;
}
