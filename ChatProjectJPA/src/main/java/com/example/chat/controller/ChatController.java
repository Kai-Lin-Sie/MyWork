package com.example.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.chat.model.Chat;
import com.example.chat.service.Impl.ChatServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@CrossOrigin
@RestController
@RequestMapping("chat")
public class ChatController {
	@Autowired
	ChatServiceImpl csi;
	
	@GetMapping("allChat")
	public Page<Chat> getAllChat(@RequestHeader("Authorization") String authHeader,
			Pageable pageable)
	{	
		//檢查授權標頭是否存在且以 "Bearer" 開頭
//		if(authHeader != null && authHeader.startsWith("Bearer")) {
//		
//		//驗證JWT 提取token
//		String token= authHeader.replace("Bearer", "");
		// 在這裡進行 JWT 的驗證和解析
//		try {
//			Claims claims =Jwts.parser()
//					.setSigningKey("1234567890")
//					.parseClaimsJws(token)
//					.getBody();
//			// 獲取用戶名或其他信息
//			String username=claims.getSubject();
//			// 認證成功，獲取聊天記錄
//            return csi.getAllChat();
//		}
//		catch(Exception e){
//			 // 如果 token 無效，處理例外
//			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid token");
//			} 
		if(authHeader != null && authHeader.equals("token")) {
			// 檢查授權標頭是否存在且為簡單 token
			
			 // 認證成功，獲取聊天記錄
            return csi.getAllChat(pageable);
		}else {
			// 如果沒有提供 token，返回未授權錯誤
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
					"Authorization header is missing or invalid");
		}
	}
	
	@GetMapping("/{id}")
	public Chat getChatById(@PathVariable int id) {
		return csi.getChatById(id);
	}
	
	@PostMapping("addChat")
	public Chat addChat(@RequestBody Chat c) {
		return csi.saveChat(c);
		
	}
	
	@PutMapping("updateChat/{id}")
	public Chat updateChat(@PathVariable int id,@RequestBody Chat c) {
		return csi.updateChat(id, c);
	}
	
	@DeleteMapping("deleteChat/{id}")
	public void deleteChat(@PathVariable int id){
		csi.deleteChat(id);
	}
	

	
}
