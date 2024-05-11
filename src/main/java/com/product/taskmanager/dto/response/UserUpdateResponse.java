package com.product.taskmanager.dto.response;

import com.product.taskmanager.dto.team.TeamReadDTO;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class UserUpdateResponse {
    private String name;
    private TeamReadDTO team;
}
