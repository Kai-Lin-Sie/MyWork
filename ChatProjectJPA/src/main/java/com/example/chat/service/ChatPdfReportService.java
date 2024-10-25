package com.example.chat.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chat.dao.ChatMapper;
import com.example.chat.model.Chat;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ChatPdfReportService {
	@Autowired
	ChatMapper cm;
	
	public byte[] ChatReport()
	{
		try{
			//測試載入報告.jrxml的文件是否存在
			InputStream inputStream = getClass().getResourceAsStream("/reports/test2.jrxml");
			if (inputStream == null) {
			    System.out.println("Report file not found!");
			} else {
			    System.out.println("Report file found!");
			}
			
			//載入報告
			String filePath="src/main/resources/reports/test2.jrxml";//絕對路徑
			JasperReport jasr = JasperCompileManager.compileReport(filePath);
			if(jasr.getColumnCount() == 0) {
				System.out.println("No data found!");
			}else {
				System.out.println("Report compiled successfully.");
			}
			
			//獲取資料數據
			List<Chat> allChat = cm.findAll();
			//測試資料是否進入
			if (allChat.isEmpty()) {
			    System.out.println("No data found!");
			} else {
			    System.out.println("Data found: " + allChat.size() + " records.");
			}
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allChat);
			
			//報告參數設定
			Map<String,Object> parameters = new HashMap<>();
			
			
			//產生報告
			JasperPrint jasp = JasperFillManager.fillReport(jasr, parameters, dataSource);
			
			//匯出成PDF
			ByteArrayOutputStream opstr = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasp, opstr);
		
			return opstr.toByteArray();
		}catch(JRException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
