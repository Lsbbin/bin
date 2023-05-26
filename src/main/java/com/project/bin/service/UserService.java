package com.project.bin.service;

import com.project.bin.dto.entity.UserEntity;

import java.util.Map;

public interface UserService {
    
    void signUp(UserEntity userEntity) throws Exception;

    Map loginProc(String userId, String userPwd) throws Exception;
}
