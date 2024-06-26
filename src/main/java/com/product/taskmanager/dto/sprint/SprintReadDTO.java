package com.product.taskmanager.dto.sprint;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class SprintReadDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
