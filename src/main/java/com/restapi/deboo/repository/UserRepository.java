package com.restapi.deboo.repository;

import com.restapi.deboo.entity.UserEntity;
import com.restapi.deboo.vo.UserVO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    List<UserEntity> findAllByPhone(String phone);

    @Query(value = "SELECT * FROM user WHERE id = ?1", nativeQuery = true)
    List<UserEntity> loadUserByUsername(String id);

    @Query(value = "SELECT * FROM user WHERE phone = ?1", nativeQuery=true)
    UserEntity findOneByUserPhone(String phone);

    @Query(value = "SELECT * FROM user WHERE access_token = ?1", nativeQuery=true)
    UserEntity findOneByUserAccessToken(String phone);

    @Override
    List<UserEntity> findAll();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user(name, gender, email, phone, type, auth, is_active, access_token) VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery=true)
    Integer create(String name, int gender, String email, String phone, int type, String auth, boolean isActive, String access_token);
}
