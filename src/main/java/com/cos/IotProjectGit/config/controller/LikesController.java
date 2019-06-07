package com.cos.IotProjectGit.config.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.IotProjectGit.model.Likes;
import com.cos.IotProjectGit.model.User;
import com.cos.IotProjectGit.repository.LikesRepository;
import com.cos.IotProjectGit.repository.UserRepository;
import com.cos.IotProjectGit.service.UserCustomerDetail;

@Controller
public class LikesController {
	
	@Autowired
	private LikesRepository likesRepository;
	

	@Autowired
	private UserRepository userRepository;
	
	//applicant -> company
	//함수
	//로그인이 되어 있으면 user객체
	//Like 객체 생성
	//likeService.save();
	@PostMapping("/like/{userId}")
	public @ResponseBody String like(@PathVariable int userId,@AuthenticationPrincipal UserCustomerDetail userDetail){
		System.out.println("일단되냐?");
		Optional<User> userO = userRepository.findById(userId);
		System.out.println(userO+"넌왔어?");
		System.out.println(userO.get()+"니가문제니?");
		User fromUser = userDetail.getUser();
		System.out.println("fromuser"+fromUser);
		User toUser = userO.get();
		System.out.println("toUser"+toUser);
		
		Likes like = new Likes();
		like.setFromUser(fromUser);
		like.setToUser(toUser);
		System.out.println(like);
		likesRepository.save(like);
		
		return "like ";
	}
	
	@PostMapping("/unlike/user/{id}")
	public @ResponseBody String unlike(@PathVariable int id, @AuthenticationPrincipal UserCustomerDetail userDetail) {
		//User, Image
		Optional<User> optionalToUser = userRepository.findById(id);
		System.out.println("id : "+id);
		System.out.println("userId : "+userDetail.getUser().getId());
		
		User fromUser = userDetail.getUser();
		User toUser = optionalToUser.get();
		likesRepository.deleteByFromUserIdAndToUserId(fromUser.getId(), toUser.getId());
		return "unlike ok";
	}
		
}
