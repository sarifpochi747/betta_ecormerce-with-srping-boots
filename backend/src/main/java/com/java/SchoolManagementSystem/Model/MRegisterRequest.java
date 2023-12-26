package com.java.SchoolManagementSystem.Model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MRegisterRequest {
    private String name;
    private String email;
    private String password;
}
