package com.cos.IotProjectGit.config.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.IotProjectGit.model.ApplicantHistory;
import com.cos.IotProjectGit.model.User;
import com.cos.IotProjectGit.repository.ApplicantHistoryRepository;
import com.cos.IotProjectGit.service.UserCustomerDetail;

@Controller
public class ApplicantHistoryController {
	
	@Autowired
	ApplicantHistoryRepository applicanthistoryrepository;
	
	
	@PostMapping("history/create")
	
	public @ResponseBody ApplicantHistory historyCreate(@AuthenticationPrincipal UserCustomerDetail userDetail,
			String companyName,String companyContent) throws IOException {
		
		User user = userDetail.getUser();
		
		ApplicantHistory applicanthistory = ApplicantHistory.builder().user(user).companyName(companyName)
				.companyContent(companyContent).build();
		
	    applicanthistoryrepository.save(applicanthistory);
		
		return applicanthistory; 

}
	@GetMapping("history")
	public String history() {
		return "history";
	}

}
