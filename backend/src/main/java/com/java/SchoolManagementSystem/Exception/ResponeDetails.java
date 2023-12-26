package com.java.SchoolManagementSystem.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Date;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponeDetails {
    private String message;
    private Date date;
    private ResponseEntity responseEntity;



}
