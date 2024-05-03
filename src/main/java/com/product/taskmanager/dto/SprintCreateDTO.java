package com.product.taskmanager.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class SprintCreateDTO {
    private String name;
    private ZonedDateTime startDate;
    private ZonedDateTime finishDate;
    private String description;
}
