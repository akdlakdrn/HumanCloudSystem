package com.cos.IotProjectGit.config.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.IotProjectGit.ResponseDto.ResponseDto;
import com.cos.IotProjectGit.model.ApplicantResume;
import com.cos.IotProjectGit.model.CompanyResume;
import com.cos.IotProjectGit.model.User;
import com.cos.IotProjectGit.repository.ApplicantResumeRepository;
import com.cos.IotProjectGit.repository.CompanyResumeRepository;
import com.cos.IotProjectGit.repository.UserRepository;
import com.cos.IotProjectGit.service.UserCustomService;
import com.cos.IotProjectGit.service.UserCustomerDetail;


//Role, Applicant, Company, Admin 
@Controller
public class UserController {

	private final static String TAG = "UserController : ";
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserCustomService userCustomService;
	
	@Autowired
	private ApplicantResumeRepository applicantResumeRepository;
	
	@Autowired
	private CompanyResumeRepository companyResumeRepository;
	



	//메인페이지!! (동적:Applicant, Company)
	@GetMapping("/")
	public String home() {
		return "login";
	}

	@GetMapping("/user/login")
	public String userLogin() {

		return "login";
	}
	
	
	@GetMapping("/user/main")
	public String userLoginproc(@AuthenticationPrincipal UserCustomerDetail userDetail,Model model) {
		Optional<User> userO= userRepository.findById(userDetail.getUser().getId());
		User user= userO.get();
		System.out.println(user.getRole());
		
	
		List<ApplicantResume> applicantResume = applicantResumeRepository.findAll();
		
		List<ApplicantResume> applicantResumelist = applicantResumeRepository.findByUserId(user.getId());
		
		  List<CompanyResume> companyResume = companyResumeRepository.findAll();
		  List<CompanyResume> companyResumelist = companyResumeRepository.findByUserId(user.getId());
		  companyResumeRepository.findByUserId(user.getId());
		 
		// @formatter:on

		model.addAttribute("applicantResumelist", applicantResumelist);
		model.addAttribute("applicantResume", applicantResume);
	
		
		 model.addAttribute("companyResume",companyResume);
		 model.addAttribute("companyResumelist",companyResumelist);
		 
		
		model.addAttribute("user", user);
	
		return "index";
	}

	@GetMapping("/user/applicant/join")
	public String userApplicantJoin() {
		return "user/join";
	}
	
	@GetMapping("/user/company/join")
	public String userCompanyJoin() {
		return "company/join";
	}

	@PostMapping("/user/create")
	public String userCreate(User user) {
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user);
		return "login";
	}
	
	@PostMapping("/api/user/create")
	public void apiUserCreate(User user) {
		System.out.println(user);
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		
		user.setPassword(encPassword);
		userRepository.save(user);
	}
	
	@PostMapping("/user/update")
	public String userUpdate(User user) {
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user);
		return "index";
	}
	
	
	
	@GetMapping("/api/user/login")
	public @ResponseBody User login(String username,String password) {
		User user = userRepository.findByUsername(username); 
		
	
		ResponseDto Login = new ResponseDto(); 
		if(user !=null) {
			System.out.println("유저이름"+user.getName());
			System.out.println("유저비번"+user.getUsername());
			Boolean psCheck = passwordEncoder.matches(password,user.getPassword());
			System.out.println(psCheck);
			return user;
		}else {
			return null;
		}
	}
//	@GetMapping("api/loginProcess")
//	  public @ResponseBody LoginState apiLoginProc(String username, String password) {
//	    Users findUser = userService.apiLoginFind(username);
//	    LoginState ls = new LoginState();
//	    if(findUser!=null) {
//	      System.out.println("로그인 유저 정보:"+findUser);
//	      System.out.println("로그인한 유저 원시패스워드:"+password);
//	      System.out.println("DB 엔코드 패스워드 :"+findUser.getPassword());
//	      Boolean passCheck = passwordEncoder.matches(password, findUser.getPassword());
//	      System.out.println("매칭 확인:"+passCheck);
//	      if(passCheck) {
//	        ls.setResult("user login success");
//	      }else{
//	        ls.setResult("user login false");
//	      }
//	    }else {
//	      ls.setResult("user not found");
//	    }
//	    return ls;
//	  }
//	
	//Role만 확인하면 됨!!
	@GetMapping("/user/{id}")
	public String userDetail(@PathVariable int id, @AuthenticationPrincipal UserCustomerDetail userDetail, Model model) {
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
	@PostMapping("/checkid/{username}")
	   public void nameCheck(@PathVariable String username, HttpServletResponse response) 
	         throws IOException {
	      System.out.println(username);
	      PrintWriter out = response.getWriter();
	      User findUsername = userRepository.findByUsername(username);
	      if(findUsername != null) {
	         out.print(-1);
	      }else {
	         out.print(1);
	      }
	   }

}
