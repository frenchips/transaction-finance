package com.example.test.repository;

import com.example.test.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query(value = """
                select 
                    u.name,
                    a.balance as saldo,
                    t.type,
                    t.amount
                from tb_transaction t
                inner join tb_account a on t.account_id = a.id
                inner join tb_user u on a.user_id = u.id
            """,
            countQuery = """
                select 
                    COUNT(1)
                from tb_transaction t
                inner join tb_account a on t.account_id = a.id
                inner join tb_user u on a.user_id = u.id
                    """, nativeQuery = true)
    Page<Object[]> search(Pageable pageable);

    @Query(value = """
                select 
                     t.type,
                     SUM(t.amount) as total_amount,
                     (select COUNT(1) from tb_transaction) as total_transaction
                from tb_transaction t
                inner join tb_account a on t.account_id = a.id
                inner join tb_user u on a.user_id = u.id
                where (:type is null or :type = '' or  upper(t.type) like upper(:type))  
                and (:startDate is null OR t.create_at >= DATE(:startDate))  
                and (:endDate is null OR t.create_at >= DATE(:endDate))
                group by t.type
            """, nativeQuery = true)
    List<Object[]> report(@Param("type") String type,
                          @Param("startDate")Date startDate,
                          @Param("endDate") Date endDate);
}
