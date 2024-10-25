package com.example.chat.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.chat.dao.ChatMapper;
import com.example.chat.model.Chat;
import com.example.chat.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService{
	@Autowired
	ChatMapper cm;
	
	@Override
	public Chat saveChat(Chat c) {
		// TODO Auto-generated method stub
		return cm.save(c);
	}

	@Override
	public Page<Chat> getAllChat(Pageable pageable) {
		// TODO Auto-generated method stub
		return cm.findAll(pageable);
	}

	@Override
	public Chat getChatById(int id) {
		// TODO Auto-generated method stub
		return cm.getById(id);
	}


	@Override
	public Chat updateChat(int id, Chat c) {
		// TODO Auto-generated method stub
		c.setId(id);
		
		return cm.save(c);
	}

	@Override
	public void deleteChat(int id) {
		// TODO Auto-generated method stub
		cm.deleteById(id);
	}
	
	
}
