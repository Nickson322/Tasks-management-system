package com.product.taskmanager.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserUpdateRequest {
    private String name;
    private String userRole;
    private String email;
}
