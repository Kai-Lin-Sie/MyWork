package com.example.chat.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.chat.model.Chat;

public interface ChatService {
	
	//create
	Chat saveChat(Chat c);
	
	//read
	Page<Chat> getAllChat(Pageable pageable);//支持分頁
	Chat getChatById(int id);
	//List<Chat> getChatByName(String name);
	
	//update
	Chat updateChat(int id,Chat c);
	
	//delete
	void deleteChat(int id);
	

	
	
}
