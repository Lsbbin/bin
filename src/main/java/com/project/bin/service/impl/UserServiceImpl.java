package com.project.bin.service.impl;

import com.project.bin.cmmn.exception.ApiException;
import com.project.bin.cmmn.util.AES256;
import com.project.bin.dto.UserDto;
import com.project.bin.dto.entity.UserEntity;
import com.project.bin.dto.enums.ExceptionType;
import com.project.bin.repository.UserRepository;
import com.project.bin.service.LoginService;
import com.project.bin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, LoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserEntity userEntity) throws Exception {
        AES256 aes256 = new AES256();

        userRepository.save(userEntity);

        userEntity.hashPassword(passwordEncoder);
        userEntity.encrypt(aes256);
    }

    @Override
    public UserDto verify(String userId, String userPwd) throws Exception {
        AES256 aes256 = new AES256();
        String encId = aes256.encrypt(userId);

        UserDto user = userRepository.findByUserId(encId);
        String password = userRepository.findPasswordById(encId);

        if(user == null || !passwordEncoder.matches(userPwd, password)) {
            throw new ApiException(ExceptionType.DATA_NOT_FOUND);
        }

        user.decrypt(aes256);

        return user;
    }


}
