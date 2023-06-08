package com.project.bin.repository.custom;


import com.project.bin.dto.UserDto;

import java.util.List;


public interface UserRepositoryCustom {

    UserDto findByUserId(String id);

    String findPasswordById(String id);

    List<UserDto> getUserList();
}
