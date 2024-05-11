package com.product.taskmanager.dto.response;

import lombok.Data;
import lombok.Setter;

@Setter
@Data
public class TeamCreationResponse {
    private String name;
    private String description;
}
