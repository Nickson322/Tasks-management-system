package com.product.taskmanager.dto.user;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;

    private String userRole;

    private String email;

    private String password;

    private String teamName;
}
