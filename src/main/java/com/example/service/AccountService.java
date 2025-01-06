package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    //initializing account repository for use
    private AccountRepository accountRepository;

    //constructor for account service to use account repository with injection 
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    //using repository save to add new acount
    public Account register(Account newAccount){
        if(newAccount.getPassword().length() < 4 || newAccount.getUsername().isEmpty()){
            return null;
        }
        if(accountRepository.existsByUsername(newAccount.getUsername())){
            return null;
        }

        Account registeredAccount = accountRepository.save(newAccount);
        return registeredAccount;
    }

    //using repository custom method to find username and password to login 
    public Account login(String username, String password){
        Account loginAccount = accountRepository.findByUsernameAndPassword(username, password);
        return loginAccount;
    }

}
