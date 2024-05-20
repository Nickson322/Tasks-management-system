package com.product.taskmanager.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;

@Data
@Getter
@Setter
public class SprintCreationRequest {
    private String name;
    private Duration duration;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
}
