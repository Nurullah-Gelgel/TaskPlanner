package com.planner.TaskPlanner.Service;

import com.planner.TaskPlanner.Payload.UserDto;
import com.planner.TaskPlanner.Payload.UserResponse;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserResponse getAllUsers(int pageNo, int pageSize, String sortBy,String sortOrder);

    UserDto getUserById(Long id);

    UserDto updateUser(UserDto userDto, Long id);

    void deleteUser(Long id);
}
