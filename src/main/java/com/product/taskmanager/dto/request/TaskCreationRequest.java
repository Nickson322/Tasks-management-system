package com.product.taskmanager.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class TaskCreationRequest {
    private String name;
    private String priority;
    private String type;
    private String status;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private String timeSpent;
    private String description;
    private String authorName;
}
