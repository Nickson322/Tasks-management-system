package com.product.taskmanager.dto.response;

import com.product.taskmanager.model.Team;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Data
@Getter
@Setter
public class SprintReadResponse {
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;
    private Team team;
}
