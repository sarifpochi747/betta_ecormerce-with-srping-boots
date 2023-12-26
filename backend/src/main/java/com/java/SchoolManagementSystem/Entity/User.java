package com.java.SchoolManagementSystem.Entity;

import com.java.SchoolManagementSystem.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "users")
public class User {

    @SequenceGenerator(
            name ="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Id
    private Long Id;

    @Column(nullable = false,length = 60)
    private String name;

    @Column(nullable = false,length = 60,unique = true)
    private String email;

    @Column(nullable = false,length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;




}
