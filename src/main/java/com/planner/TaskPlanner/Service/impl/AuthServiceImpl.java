package com.planner.TaskPlanner.Service.impl;

import com.planner.TaskPlanner.Entities.Role;
import com.planner.TaskPlanner.Entities.Users;
import com.planner.TaskPlanner.Payload.LoginDto;
import com.planner.TaskPlanner.Payload.RegisterDto;
import com.planner.TaskPlanner.Repository.RoleRepository;
import com.planner.TaskPlanner.Repository.UserRepository;
import com.planner.TaskPlanner.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                            RoleRepository roleRepository,
                            PasswordEncoder passwordEncoder) {

        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(LoginDto loginDto) {

      Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return "Login Successful";
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return "Username is already taken";
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return "Email is already taken";
        }
        Users user = new Users();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully";


    }
}
