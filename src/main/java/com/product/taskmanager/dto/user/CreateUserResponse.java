package com.product.taskmanager.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserResponse {
    private String name;
    private String userRole;
    private String email;
//    private CreateTeamResponse team;
}
