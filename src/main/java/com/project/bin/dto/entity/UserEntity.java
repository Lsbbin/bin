package com.project.bin.dto.entity;

import com.project.bin.cmmn.util.AES256;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "tbUser")
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userSn;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    @Column(nullable = false, length = 30)
    private String userName;

    @Column(nullable = false)
    private String userEmail;

    @Column(updatable = false)
    private String registerIp;

    private String updaterIp;

    public UserEntity create (String userId, String userPwd, String userName, String userEmail, String accessIp) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userEmail = userEmail;
        this.registerIp = accessIp;
        this.updaterIp = accessIp;
        return this;
    }

    public UserEntity hashPassword (PasswordEncoder passwordEncoder) {
        this.userPwd = passwordEncoder.encode(this.userPwd);
        return this;
    }

    public UserEntity encrypt (AES256 aes256) throws Exception {
        this.userId = aes256.encrypt(this.userId);
        this.userEmail = aes256.encrypt(this.userEmail);
        return this;
    }

}
