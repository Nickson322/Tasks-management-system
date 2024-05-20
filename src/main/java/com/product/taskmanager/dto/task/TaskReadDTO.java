package com.product.taskmanager.dto.task;

import java.time.Duration;
import java.time.ZonedDateTime;

public class TaskReadDTO {
    private String name;

    private String priority;

    private String type;

    private String status;

    private ZonedDateTime executionPeriod;

    private ZonedDateTime createdOn;

    private ZonedDateTime updatedOn;

    private Duration timeSpent;

    private String description;
}
