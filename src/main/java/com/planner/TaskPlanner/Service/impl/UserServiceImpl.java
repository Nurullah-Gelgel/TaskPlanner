package com.planner.TaskPlanner.Service.impl;

import com.planner.TaskPlanner.Entities.Users;
import com.planner.TaskPlanner.Exception.ResourceNotFoundException;
import com.planner.TaskPlanner.Payload.UserDto;
import com.planner.TaskPlanner.Payload.UserResponse;
import com.planner.TaskPlanner.Repository.UserRepository;
import com.planner.TaskPlanner.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        //dto to entity
        Users user = mapToEntity(userDto);
        Users newUser = userRepository.save(user);

        //entity to dto
        UserDto userResponse = mapToDto(newUser);

        return userResponse;
    }

    @Override
    public UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        List<Users> userList = userRepository.findAll(pageable).getContent();
        List<UserDto> users =userList.stream().map(this::mapToDto).collect(Collectors.toList());
        UserResponse userResponse = new UserResponse(users, userList.size(), userList.size(), pageable.getPageNumber(), pageable.getPageSize(), userList.isEmpty());
        return userResponse;
    }

    @Override
    public UserDto getUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return mapToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        Users updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);

    }

    @Override
    public void deleteUser(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }

    private UserDto mapToDto(Users user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setUsername(user.getUsername());
//        userDto.setPassword(user.getPassword());
//        userDto.setEmail(user.getEmail());
//        userDto.setRole(user.getRole());
        return userDto;
    }

    private Users mapToEntity(UserDto userDto) {
        Users users = modelMapper.map(userDto, Users.class);
        return users;
    }
}
