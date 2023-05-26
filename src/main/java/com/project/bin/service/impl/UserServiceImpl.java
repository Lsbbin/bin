package com.project.bin.service.impl;

import com.project.bin.cmmn.exception.ApiException;
import com.project.bin.cmmn.util.AES256;
import com.project.bin.dto.entity.UserEntity;
import com.project.bin.dto.enums.ExceptionType;
import com.project.bin.repository.UserRepository;
import com.project.bin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserEntity userEntity) throws Exception {
        AES256 aes256 = new AES256();
        userRepository.save(userEntity);

        userEntity.hashPassword(passwordEncoder); // 비밀번호 암호화
        userEntity.encrypt(aes256); // 개인정보 암호화
    }

    @Override
    public Map loginProc(String userId, String userPwd) throws Exception {
        Map responseMap = new HashMap();
        AES256 aes256 = new AES256();
        UserEntity user = userRepository.findByUserId(aes256.encrypt(userId));

        if(user == null || !user.checkPassword(userPwd, passwordEncoder)) throw new ApiException(ExceptionType.DATA_NOT_FOUND);

        responseMap.put("userSn", user.getUserSn());
        responseMap.put("userId", aes256.decrypt(user.getUserId()));
        responseMap.put("userName", user.getUserName());
        responseMap.put("userEmail", aes256.decrypt(user.getUserEmail()));
        responseMap.put("registDate", user.getRegistDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));

        return responseMap;
    }


}
