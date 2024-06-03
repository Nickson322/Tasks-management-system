package com.product.taskmanager.dto.response;

import com.product.taskmanager.dto.task.TaskReadDTO;
import com.product.taskmanager.dto.user.UserReadDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ProjectCreationResponse {
    private Long id;

    private String name;

    private String description;

    private String leaderName;


    private List<UserReadDTO> users;

    private List<TaskReadDTO> tasks;

}
