package com.example.QuanLyDoiBong.repository;

import com.example.QuanLyDoiBong.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "select * from accounts where user_name = ?1 and  pass_word = ?2", nativeQuery = true)
    Account findAccountByPassWordAndUserName(String userName, String passWord);
}
