package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    Account findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
    boolean existsByAccountId(int accountId);
    Account findByUsername(String username);
    Account findByAccountId(Integer accountId);
    
}
