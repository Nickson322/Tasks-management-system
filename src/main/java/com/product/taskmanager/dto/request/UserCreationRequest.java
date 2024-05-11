package com.product.taskmanager.dto.request;

import lombok.Getter;

@Getter
public class UserCreationRequest {
    private String name;
    private String password;
    private String email;
}
