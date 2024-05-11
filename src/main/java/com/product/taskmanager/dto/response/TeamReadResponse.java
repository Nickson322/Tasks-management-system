package com.product.taskmanager.dto.response;

import com.product.taskmanager.dto.user.UserReadDTO;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Setter
@Data
public class TeamReadResponse {
    private String name;
    private String description;
    private List<UserReadDTO> users;
}
