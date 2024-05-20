package com.product.taskmanager.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class TaskCreationResponse {
    private Long id;
    private String name;
    private String priority;
    private String type;
    private String status;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private String timeSpent;
    private String description;
    private UserReadResponse author;
}
