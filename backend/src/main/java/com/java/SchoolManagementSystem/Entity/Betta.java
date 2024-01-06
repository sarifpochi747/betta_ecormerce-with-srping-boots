package com.java.SchoolManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "betta")
public class Betta {
    @SequenceGenerator(
            name ="betta_sequence",
            sequenceName = "betta_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "betta_sequence"
    )
    @Id
    private long Id;
    private String gender;
    private String colour;
    private String date;
    private long size;
    private Integer price;
    private  String image;
}
