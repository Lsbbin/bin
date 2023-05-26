package com.project.bin.service;

import com.project.bin.dto.UserDto;

public interface LoginService {

    UserDto verify(String userId, String userPwd) throws Exception;
}
