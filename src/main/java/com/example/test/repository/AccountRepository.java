package com.example.test.repository;

import com.example.test.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query(value = """
                select 
                    u.name,
                    u.email,
                    a.balance
                from tb_account a
                inner join tb_user u on a.user_id = u.id 
            """, nativeQuery = true)
    List<Object[]> findAccountAll();
}
