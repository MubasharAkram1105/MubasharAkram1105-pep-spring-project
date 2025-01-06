package com.example.controller;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }
    //register account 
    @PostMapping("register")
    public ResponseEntity<Account> register(@RequestBody Account newAccount){

        Account registeredAccount = accountService.register(newAccount);

        if(registeredAccount != null){
        return ResponseEntity.status(200).body(registeredAccount);
        } else {
            return ResponseEntity.status(409).body(null);
        }
    }
    //login account
    @PostMapping("login")
    public ResponseEntity<Account> login(@RequestBody Account account) throws AuthenticationException{

        Account loginAccount = accountService.login(account.getUsername(), account.getPassword());

        if(loginAccount != null){
            return ResponseEntity.status(200).body(loginAccount);
        } else{
            return ResponseEntity.status(401).body(null);
        }
    }
    //create message
    @PostMapping("messages")
    public ResponseEntity<Message> create(@RequestBody Message message){

        Message createdMessage = messageService.createMessage(message);

        if(createdMessage != null){
            return ResponseEntity.status(200).body(createdMessage);
        } else{
            return ResponseEntity.status(400).body(null);
        }
    }
    //get all messages
    @GetMapping("messages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }
    //get specific message
    @GetMapping("messages/{message_id}")
    public ResponseEntity<Message> findMessageById(@PathVariable int message_id){
        return ResponseEntity.status(200).body(messageService.findMessage(message_id));
    }
    //delete message
    @DeleteMapping("messages/{message_id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable int message_id){
        if(messageService.deleteMessageById(message_id) == 1){
            return ResponseEntity.status(200).body(1);
        } else {
            return ResponseEntity.status(200).body(null);
        }
    }

    
    //update message 
    @PatchMapping("messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int message_id, @RequestBody Message message){
        
        if(messageService.updateMessage(message_id, message.getMessageText()) != null){
        return ResponseEntity.status(200).body(1);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesFromAccount(@PathVariable int account_id){
        List<Message> messagesWithId = messageService.getAllMessagesWithAccountId(account_id);
        return ResponseEntity.status(200).body(messagesWithId);
    }
    
}
