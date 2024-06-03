package com.product.taskmanager.dto.task;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@Getter
@Setter
public class TaskReadDTO {
    private String name;

    private String priority;

    private String type;

    private String status;

    private LocalDate executionPeriod;

    private LocalDate createdOn;

    private LocalDate updatedOn;

    private Duration timeSpent;

    private String description;
}
