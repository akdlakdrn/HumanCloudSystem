package com.cos.IotProjectGit.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.IotProjectGit.model.Notice;
import com.cos.IotProjectGit.repository.NoticeRepository;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeRepository noticeRepository;

	
	@PostMapping("/notice/create")
	public @ResponseBody Notice noticeCreate(Notice notice){
		
		noticeRepository.save(notice);
		
		return notice;
	}
}
