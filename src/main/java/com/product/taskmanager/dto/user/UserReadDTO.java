package com.product.taskmanager.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserReadDTO {
    private Long id;
    private String name;
    private String userRole;
    private String email;
}
