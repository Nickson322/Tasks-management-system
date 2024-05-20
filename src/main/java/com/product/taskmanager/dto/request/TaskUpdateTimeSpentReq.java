package com.product.taskmanager.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TaskUpdateTimeSpentReq {
    private String timeSpent;
}
