package com.restapi.deboo.repository;

import com.restapi.deboo.entity.RequestEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<RequestEntity, Integer> {


    @Query(value = "SELECT * FROM loan_request WHERE id = ?1 AND user_id = ?2", nativeQuery = true)
    List<RequestEntity> findByUserId(int id, int user_id);


    @Query(value = "SELECT * FROM loan_request WHERE user_id = ?1", nativeQuery = true)
    List<RequestEntity> findAllByUserId(int user_id);
}
