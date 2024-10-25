package com.example.chat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chat.model.ChatUser;

public interface ChatUserDao extends JpaRepository<ChatUser,Integer>{
	ChatUser findByUsername(String username);
}
