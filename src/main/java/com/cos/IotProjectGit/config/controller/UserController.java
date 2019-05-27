package com.cos.IotProjectGit.config.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cos.IotProjectGit.ResponseDto.ResponseDto;
import com.cos.IotProjectGit.model.ApplicantResume;
import com.cos.IotProjectGit.model.User;
import com.cos.IotProjectGit.repository.ApplicantResumeRepository;
import com.cos.IotProjectGit.repository.UserRepository;
import com.cos.IotProjectGit.service.UserCustomService;
import com.cos.IotProjectGit.service.UserCustomerDetail;


//Role, Applicant, Company, Admin 
@Controller
public class UserController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserCustomService userCustomService;
	
	@Autowired
	private ApplicantResumeRepository applicantResumeRepository;

	//메인페이지!! (동적:Applicant, Company)
	@GetMapping("/")
	public String home() {
		return "login";
	}

	@GetMapping("/user/login")
	public String userLogin() {
		return "user/login";
	}
	
	@PostMapping("/user/loginProc")
	public String userLoginproc() {
		return "index";
	}

	@GetMapping("/user/applicant/join")
	public String userApplicantJoin() {
		return "user/applicant/join";
	}
	
	@GetMapping("/user/company/join")
	public String userCompanyJoin() {
		return "user/company/join";
	}

	@PostMapping("/user/create")
	public String userCreate(User user) {
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user);
		return "user/login";
	}
	
	
	@PostMapping("/app/user/login")
	public ResponseDto login(@RequestBody User user) {
		
		User userO = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		if(userO !=null) {
			return new ResponseDto(1,"ok");
		}else {
			return new ResponseDto(-1, "fail");
		}
	}
	
	//Role만 확인하면 됨!!
	@GetMapping("/user/{id}")
	public String userDetail(@PathVariable int id, Model model) {
		Optional<User> temp = userCustomService.userDetail(id);
		// 숙제 널처리하기(Optional)
		// ispresent()

		if (temp.isPresent()) {
			User user = temp.get();
			model.addAttribute("user", user);
			return "user/detail";
		} else {
			return "error";
		}

	}
	
	/*
	 * @GetMapping("/user/{id}") public String userDetail(@PathVariable int
	 * id, @AuthenticationPrincipal UserCustomerDetail userDetail, Model model) {
	 * //user 유저 디터일 정보 Optional<User> userO =
	 * userRepository.findById(userDetail.getUser().getId()); User user =
	 * userO.get(); Optional<User> resumeUserO = userRepository.findById(id);
	 * 
	 * User resumeUser = resumeUserO.get();
	 * 
	 * 
	 * }
	 */

}
