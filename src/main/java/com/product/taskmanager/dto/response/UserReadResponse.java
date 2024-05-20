package com.product.taskmanager.dto.response;

import com.product.taskmanager.dto.team.TeamReadDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UserReadResponse {
    private String name;
    private String userRole;
    private String email;
    private TeamReadDTO team;
}
