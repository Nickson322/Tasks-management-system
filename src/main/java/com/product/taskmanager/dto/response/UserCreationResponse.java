package com.product.taskmanager.dto.response;

import lombok.Data;
import lombok.Setter;

@Setter
@Data
public class UserCreationResponse {
    private String name;
    private String userRole;
    private String email;
}
