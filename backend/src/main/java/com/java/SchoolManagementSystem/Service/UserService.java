package com.java.SchoolManagementSystem.Service;

import com.java.SchoolManagementSystem.Entity.User;
import com.java.SchoolManagementSystem.enums.UserRole;
import com.java.SchoolManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User crateUser(String name,String email,String password){
        //create user data
        User user = User.builder().email(email).name(name).password(passwordEncoder.encode(password)).role(UserRole.STUDENT).build();
        userRepository.save(user);
        return user;
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public  Boolean matchPassword(String rawPassword,String encodePassword){
        return passwordEncoder.matches(rawPassword,encodePassword);
    }

    public String generateToeken(User user){

        return jwtService.generateToken(user);
    }

    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(long Id) {
        return userRepository.findById(Id);
    }
}
