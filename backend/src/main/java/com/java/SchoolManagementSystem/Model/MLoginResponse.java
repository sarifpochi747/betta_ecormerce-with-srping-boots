package com.java.SchoolManagementSystem.Model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MLoginResponse {
    private String jwt;
    private String role;
}
