package com.java.SchoolManagementSystem.BusinessLogic;

import com.java.SchoolManagementSystem.Entity.User;
import com.java.SchoolManagementSystem.Mapper.UserMapper;
import com.java.SchoolManagementSystem.Model.*;
import com.java.SchoolManagementSystem.Service.UserService;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserBusinessLogic {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserBusinessLogic(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    public MRegisterRespone resgister(MRegisterRequest request){
        //validation
        if(Objects.isNull(request.getEmail())){
            //throw error
            throw new IllegalArgumentException("Email NULL");

        }
        if(Objects.isNull(request.getName())){
            //throw error
            throw new IllegalArgumentException("Name NULL");

        }
        if(Objects.isNull(request.getPassword())){
            //throw error
            throw new IllegalArgumentException("Password NULL");
        }

        //verification
        //check email exist
        if(userService.existsByEmail(request.getEmail())){
            //throw error
            throw new RuntimeException("Email exist already");
        }

        User user = userService.crateUser(request.getName(),request.getEmail(),request.getPassword());

        //TODO :Mapper structure User to MRegisterRespone
        return userMapper.toRegisterRespone(user);

    }

    public MLoginResponse login(MLoginRequest request) {
        //validation
        if(Objects.isNull(request.getEmail())){
            //throw error
            throw new IllegalArgumentException("email NULL");

        }
        if(Objects.isNull(request.getPassword())){
            throw new IllegalArgumentException("Password NULL");
        }

        //verify with database
        // check email in database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            throw new RuntimeException("Email incorrect");
        }
        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(),user.getPassword())){
            //throw error login fail , password incorrect
            throw new RuntimeException("password incorrect");

        }
        String token = userService.generateToeken(user);
        String role = String.valueOf(user.getRole());

        return new MLoginResponse(token,role);
    }

    public List<UserRespone> getAllUser() {
        List<User> user = userService.getAllUser();
        List<UserRespone> userRespone = new ArrayList<>();
        for(int i =0;i<user.size();i++){
            userRespone.add(userMapper.toUserRespone(user.get(i)));
        }
        return userRespone;
    }

    public Optional<User> getUserById(long Id) {
        return userService.getUserById(Id);
    }
}
