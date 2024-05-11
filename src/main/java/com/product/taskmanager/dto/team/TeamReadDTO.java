package com.product.taskmanager.dto.team;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TeamReadDTO {
    private String name;
    private String description;
}
