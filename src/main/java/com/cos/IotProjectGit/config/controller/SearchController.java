package com.cos.IotProjectGit.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cos.IotProjectGit.repository.ApplicantHistoryRepository;
import com.cos.IotProjectGit.repository.ApplicantResumeRepository;

@Controller
public class SearchController {
	
	@Autowired
	private ApplicantHistoryRepository applicantHistoryRepository;
	
	@Autowired
	private ApplicantResumeRepository applicantResumeRepository;
	
}
