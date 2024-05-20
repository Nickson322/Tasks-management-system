package com.product.taskmanager.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TeamCreationRequest {
    private String name;
    private String description;
}
