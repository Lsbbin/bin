package com.project.bin.repository.custom;


import com.project.bin.dto.entity.UserEntity;


public interface UserRepositoryCustom {

    UserEntity findByUserId(String id);
}
