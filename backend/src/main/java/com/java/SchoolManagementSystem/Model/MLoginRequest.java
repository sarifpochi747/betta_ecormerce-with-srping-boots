package com.java.SchoolManagementSystem.Model;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MLoginRequest {
    private String email;
    private String password;
}
