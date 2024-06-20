package com.planner.TaskPlanner.Service;

import com.planner.TaskPlanner.Payload.LoginDto;
import com.planner.TaskPlanner.Payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
