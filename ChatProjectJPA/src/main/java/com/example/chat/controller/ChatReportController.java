package com.example.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chat.service.ChatPdfReportService;

@CrossOrigin
@RestController
@RequestMapping("report")
public class ChatReportController {
	@Autowired
	ChatPdfReportService cprs;
	
	@GetMapping("pdfChat")
	public ResponseEntity<byte[]> downloadPDF(){
		byte[] pdfContents = cprs.ChatReport();
		
		if(pdfContents == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		HttpHeaders hhd = new HttpHeaders();
		hhd.add("Content-Disposition", "attachment; filename=chat_report.pdf");
		hhd.add("Content-Type", "application/pdf");
		
		return new ResponseEntity<>(pdfContents,hhd,HttpStatus.OK);
	}
}
