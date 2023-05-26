package com.project.bin.repository.impl;

import com.project.bin.dto.UserDto;
import com.project.bin.dto.entity.QUserEntity;
import com.project.bin.repository.custom.UserRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QUserEntity qUserEntity = QUserEntity.userEntity;

    @Override
    public UserDto findByUserId (String id) {
        UserDto result = queryFactory
                .select(Projections.bean(UserDto.class,
                            qUserEntity.userSn
                            , qUserEntity.userId
                            , qUserEntity.userName
                            , qUserEntity.userEmail
                            , qUserEntity.registDate))
                .from(qUserEntity)
                .where(qUserEntity.userId.eq(id))
                .fetchOne();

        return result;
    }

    @Override
    public String findPasswordById (String id) {
        String result = queryFactory
                .select(qUserEntity.userPwd)
                .from(qUserEntity)
                .where(qUserEntity.userId.eq(id))
                .fetchOne();

        return result;
    }


}
