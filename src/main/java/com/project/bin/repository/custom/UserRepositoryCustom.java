package com.project.bin.repository.custom;


import com.project.bin.dto.UserDto;


public interface UserRepositoryCustom {

    UserDto findByUserId(String id);

    String findPasswordById(String id);
}
