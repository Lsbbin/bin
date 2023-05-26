package com.project.bin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.bin.cmmn.util.AES256;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private long userSn;

    private String userId;

    private String userName;

    private String userEmail;

    private LocalDateTime registDate;

    private String registerIp;

    private LocalDateTime updateDate;

    private String updaterIp;

    public UserDto decrypt (AES256 aes256) throws Exception {
        this.userId = aes256.decrypt(this.userId);
        this.userEmail = aes256.decrypt(this.userEmail);
        return this;
    }

}
