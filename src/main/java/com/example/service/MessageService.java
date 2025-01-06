package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public boolean checkMessage(Message message){

        if(message.getMessageText().isEmpty()){
            return false;
        }
        if(message.getMessageText().length() > 225){
            return false;
        }
        if(!(accountRepository.existsByAccountId(message.getPostedBy()))){
            return false;
        }

        return true;
    }

    public Message createMessage(Message message){
        
        Message createdMessage;

        if(checkMessage(message)){
            createdMessage = messageRepository.save(message);
            return createdMessage;
        }

        return null;
    }

    public List<Message> getAllMessages(){
        return (List<Message>)messageRepository.findAll();
    }

    public Message findMessage(int messageId){
        return messageRepository.findByMessageId(messageId);
    }

    public int deleteMessageById(int messageId){
        if(messageRepository.existsById(messageId)){
            messageRepository.deleteById(messageId);
            return 1;
        } else {
            return 0;
        }
    }
    public Message updateMessage(int messageId, String updatedText){

        Message message = messageRepository.findByMessageId(messageId);
        if(message == null){
            return null;
        }
        if(updatedText.isEmpty()){
            return null;
        }
        message.setMessageText(updatedText);
        if(checkMessage(message)){
            return message;
        }

        return null;
   }
   public List<Message> getAllMessagesWithAccountId(int accountId){

        List<Message> messagesById = messageRepository.findByPostedBy(accountId);
        return messagesById;

   }
}
