package com.project.bin.service;

import com.project.bin.dto.UserDto;
import com.project.bin.dto.entity.UserEntity;

public interface UserService {
    
    void signUp(UserEntity userEntity) throws Exception;

    UserDto loginProc(String userId, String userPwd) throws Exception;
}
