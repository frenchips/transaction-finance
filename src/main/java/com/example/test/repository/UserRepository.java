package com.example.test.repository;

import com.example.test.entity.User;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "select id, name, email, create_at from tb_user ", nativeQuery = true)
    List<Object[]> findAllUser();

    @Query(value = "select name, email from tb_user where id = :id ", nativeQuery = true)
    List<Object[]> getUserById(@Param("id") Long id);
}
