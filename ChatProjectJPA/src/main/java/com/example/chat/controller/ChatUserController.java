package com.example.chat.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.example.chat.dao.ChatUserDao;
import com.example.chat.model.ChatUser;

@RestController
@RequestMapping("user")
@CrossOrigin
public class ChatUserController {
	@Autowired
	ChatUserDao cud;
	
	@PostMapping("register")
	public String register(@RequestBody ChatUser user) {
		//檢查用戶名是否存在
		if(cud.findByUsername(user.getUsername()) != null) {
			return "該用戶已存在";
		}else {
		//保存用戶
			cud.save(user);
			return "用戶註冊成功";
		}
	}
	
	@PostMapping("login")
	public ResponseEntity<Map<String, String>> login(@RequestBody ChatUser user) {
		ChatUser foundUser=cud.findByUsername(user.getUsername());
		
		if(foundUser != null && foundUser.getUserpassword()
				.equals(user.getUserpassword())) {
			//生成JWT
//			String token=Jwts.builder()
//					.setSubject(foundUser.getUsername())
//					.setExpiration(new Date(System.currentTimeMillis()+86400000))//設定過期時間
//					.signWith(SignatureAlgorithm.HS256, "1234567890")
//					.compact();
			
			String token = foundUser.getUsername();// 或者其他唯一標識符

			//返回token
			return ResponseEntity.ok(Map.of("token",token));
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("message", "帳號或密碼錯誤"));
		}
		
	}
	
}
