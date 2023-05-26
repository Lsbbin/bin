package com.project.bin.repository.impl;

import com.project.bin.dto.entity.QUserEntity;
import com.project.bin.dto.entity.UserEntity;
import com.project.bin.repository.custom.UserRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QUserEntity qUserEntity = QUserEntity.userEntity;

    @Override
    public UserEntity findByUserId (String userId) {
        return queryFactory.selectFrom(qUserEntity)
                .where(qUserEntity.userId.eq(userId))
                .fetchOne();
    }


}
