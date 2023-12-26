package com.java.SchoolManagementSystem.Mapper;

import com.java.SchoolManagementSystem.Entity.User;
import com.java.SchoolManagementSystem.Model.MRegisterRespone;
import com.java.SchoolManagementSystem.Model.UserRespone;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    MRegisterRespone toRegisterRespone(User user);
    UserRespone toUserRespone(User user);
}
