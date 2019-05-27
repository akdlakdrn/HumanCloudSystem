package com.cos.IotProjectGit.config.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.IotProjectGit.model.Likes;
import com.cos.IotProjectGit.model.User;
import com.cos.IotProjectGit.repository.LikesRepository;
import com.cos.IotProjectGit.repository.UserRepository;
import com.cos.IotProjectGit.service.LikesService;
import com.cos.IotProjectGit.service.UserCustomerDetail;

@Controller
public class LikesController {
	
	@Autowired
	private LikesRepository likeRepository;
	
	@Autowired
	private LikesService likesService;

	@Autowired
	private UserRepository userRepository;
	
	//applicant -> company
	//함수
	//로그인이 되어 있으면 user객체
	//Like 객체 생성
	//likeService.save();
	@PostMapping("/like/{userId}")
	public void test(@AuthenticationPrincipal UserCustomerDetail userDetail, @PathVariable int userId){
		
		Optional<User> userO = userRepository.findById(userId);
		User fromUser = userDetail.getUser();
		User toUser = userO.get();
		
		Likes like = new Likes();
		like.setFromUser(fromUser);
		like.setToUser(toUser);
		
		likeRepository.save(like);
		
	}
		
}
