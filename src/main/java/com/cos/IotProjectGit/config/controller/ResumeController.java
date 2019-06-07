package com.cos.IotProjectGit.config.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cos.IotProjectGit.model.ApplicantResume;
import com.cos.IotProjectGit.model.User;
import com.cos.IotProjectGit.model.code.Education;
import com.cos.IotProjectGit.repository.ApplicantResumeRepository;
import com.cos.IotProjectGit.repository.JobRepository;
import com.cos.IotProjectGit.repository.UserRepository;
import com.cos.IotProjectGit.service.ResumeService;
import com.cos.IotProjectGit.service.UserCustomerDetail;
import com.cos.IotProjectGit.util.UserUtil;

@Controller
public class ResumeController {

	@Autowired
	private ApplicantResumeRepository applicantResumeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private ResumeService applicantResumeService;

	@GetMapping("/resume")
	public String resume(@AuthenticationPrincipal UserCustomerDetail userDetail,Model model) {
		Optional<User> userO= userRepository.findById(userDetail.getUser().getId());
		User user= userO.get();
		
		
		
		model.addAttribute("user", user);
		return "/user/resume";
	}
	
	@GetMapping("/resume/company")
	public String companyresume(@AuthenticationPrincipal UserCustomerDetail userDetail,Model model) {
		Optional<User> userO= userRepository.findById(userDetail.getUser().getId());
		User user= userO.get();
		model.addAttribute("user", user);
		return "/company/resume";
	}
	

	
	
	@GetMapping("/resume/Detail")
	public String resumeDetail() {
		return "/resume/Detail";
	}
	

	
	@GetMapping("/app/resume/list")
	public @ResponseBody List<ApplicantResume> testfindAll() {
		List<ApplicantResume > resumelist = applicantResumeService.findAll();
		System.out.println("너왔냐?"+resumelist);
		return resumelist;
	}
	
	@GetMapping("/resume/list")
	public String findAll(Model model) {
		model.addAttribute("applicantresumes", applicantResumeService.findAll());// 현재는 자바객체
		return "applicantResume";
	}

	@PostMapping("/resume/create")
	public String resumeCreate(@AuthenticationPrincipal UserCustomerDetail userDetail,
			@RequestParam("file") MultipartFile file, String title, Education education , String wishAddress, 
			String job,String age, int salary, String experience,
			@RequestParam("zip") MultipartFile zip
			) throws IOException {
		
		UUID uuidfile = UUID.randomUUID();
		String uuidFileName = uuidfile + "_" + file.getOriginalFilename();
		System.out.println("uuidfile"+uuidfile);
		UUID uuidZip = UUID.randomUUID();
		String uuidZipName = uuidZip + "_" + zip.getOriginalFilename();
		
		Path filePath = Paths.get(UserUtil.getResouecePath() + uuidFileName);
		System.out.println("filePath"+filePath);
		
		System.out.println(file.getBytes());
		Files.write(filePath,file.getBytes());
		
		Path fileZip = Paths.get(UserUtil.getResouecePath()+uuidZipName);
		Files.write(fileZip, zip.getBytes());
		
		User user = userDetail.getUser();
		
		
		
		ApplicantResume applicantresume = ApplicantResume.builder().title(title).
				user(user).job(job).age(age).salary(salary).education(education).
				experience(experience).wishAddress(wishAddress).
				mimeType(file.getContentType()).fileName(uuidFileName).filePath("http://192.168.0.9:7000/image/"+uuidFileName).
				zipmimeType(zip.getContentType()).zipfileName(uuidZipName).zipfilePath("http://192.168.0.9:7000/image"+uuidZipName).
				
				build();
		
		applicantResumeRepository.save(applicantresume);
		
		return "close"; 
	}
	
//	@GetMapping("/close")
//	public String close() {
//		return "close";
//	}
	@GetMapping("/resume/{id}")
	public String detail(@PathVariable int id, Model model) {
		Optional<ApplicantResume> resume = applicantResumeService.detail(id);
		if(resume.isPresent()) {
		ApplicantResume applicantresume = resume.get();
		model.addAttribute("applicantresume", applicantresume);
		return "detail";
	}else {
		System.out.println("error");
		return "error";
	}

}
}
