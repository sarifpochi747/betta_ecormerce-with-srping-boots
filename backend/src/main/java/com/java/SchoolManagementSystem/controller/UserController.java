package com.java.SchoolManagementSystem.controller;

import com.java.SchoolManagementSystem.BusinessLogic.UserBusinessLogic;
import com.java.SchoolManagementSystem.Entity.User;
import com.java.SchoolManagementSystem.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserBusinessLogic userBusinessLogic;

    @Autowired
    public UserController(UserBusinessLogic userBusinessLogic) {
        this.userBusinessLogic = userBusinessLogic;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<MRegisterRespone> register(@RequestBody MRegisterRequest mRegisterRequest){
        return ResponseEntity.ok(userBusinessLogic.resgister(mRegisterRequest));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<MLoginResponse> login(@RequestBody MLoginRequest mLoginRequest) {
        return ResponseEntity.ok(userBusinessLogic.login(mLoginRequest));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserRespone>> getAllUser(){
        return ResponseEntity.ok(userBusinessLogic.getAllUser());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable long id){
        return ResponseEntity.ok(userBusinessLogic.getUserById(id));
    }


}
