package com.cos.IotProjectGit.config.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.IotProjectGit.model.ApplicantResume;
import com.cos.IotProjectGit.model.Comment;
import com.cos.IotProjectGit.model.CompanyResume;
import com.cos.IotProjectGit.model.User;
import com.cos.IotProjectGit.repository.ApplicantResumeRepository;
import com.cos.IotProjectGit.repository.CommentRepository;
import com.cos.IotProjectGit.repository.CompanyResumeRepository;
import com.cos.IotProjectGit.repository.UserRepository;
import com.cos.IotProjectGit.service.UserCustomerDetail;

@Controller
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ApplicantResumeRepository applicantResumeRepository;
	
	@Autowired
	private CompanyResumeRepository companyResumeRepository;
	
	
	
	@GetMapping("/view/get/{id}")
	public String getcomment(Model model, @PathVariable Integer id, @AuthenticationPrincipal UserCustomerDetail userDetail) {
		Optional<ApplicantResume> applicantResume = applicantResumeRepository.findById(id);
		Optional<List<Comment>> commentOs = commentRepository.findByToUserIdOrderByCreateDateDesc(id);
		applicantResume.get().getUser().getId();
		List<Comment> comments = new ArrayList<Comment>();
		if(commentOs.isPresent()) {
		comments=commentOs.get();
		}
		
		/*
		 * applicantResume.get().getFilePath();//jsp
		 * applicantResume.get().getZipfilePath();//이것만있으면 jsp에서보낼수있음
		 * applicantResume.get().getZipmimeType();//확장자
		 * applicantResume.get().getZipfileName();//파일
		 */		model.addAttribute("applicantResume", applicantResume.get());
		model.addAttribute("comment", comments);
		model.addAttribute("user", userDetail.getUser());
		return "/user/view";
	}
	
	@GetMapping("/view/company/{id}")
	public String getview(Model model, @PathVariable Integer id, @AuthenticationPrincipal UserCustomerDetail userDetail) {
		Optional<CompanyResume> companyResume = companyResumeRepository.findById(id);
		Optional<List<Comment>> commentOs = commentRepository.findByToUserIdOrderByCreateDateDesc(id);
		List<Comment> comments = new ArrayList<Comment>();
		if(commentOs.isPresent()) {
		comments=commentOs.get();
		}
		
		/*
		 * applicantResume.get().getFilePath();//jsp
		 * applicantResume.get().getZipfilePath();//이것만있으면 jsp에서보낼수있음
		 * applicantResume.get().getZipmimeType();//확장자
		 * applicantResume.get().getZipfileName();//파일
		 */		model.addAttribute("companyResume", companyResume.get());
		model.addAttribute("comment", comments);
		model.addAttribute("user", userDetail.getUser());
		return "/company/view";
	}
	
	@PostMapping("/comment/{id}")
	public @ResponseBody String comment(@PathVariable Integer id, @AuthenticationPrincipal UserCustomerDetail userDetail, @RequestBody Comment comment) {
		Optional<User> optionalToUser = userRepository.findById(id);
		User fromUser = userDetail.getUser();
		User toUser = optionalToUser.get();
		
		
		comment.setFromUser(fromUser);
		comment.setToUser(toUser);
		
		commentRepository.save(comment);
		
		return "1";
		
	}
}
