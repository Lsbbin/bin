package com.project.bin.service;

import com.project.bin.dto.UserDto;
import com.project.bin.dto.entity.UserEntity;

import java.util.List;

public interface UserService {
    
    void signUp(UserEntity userEntity) throws Exception;

    UserDto loginProc(String userId, String userPwd) throws Exception;

    List<UserDto> getUserList() throws Exception;
}
