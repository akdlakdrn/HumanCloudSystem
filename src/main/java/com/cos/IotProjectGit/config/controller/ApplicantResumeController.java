package com.cos.IotProjectGit.config.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.cos.IotProjectGit.repository.UserRepository;
import com.cos.IotProjectGit.service.ResumeService;
import com.cos.IotProjectGit.service.UserCustomService;
import com.cos.IotProjectGit.service.UserCustomerDetail;
import com.cos.IotProjectGit.util.UserUtil;

public class ApplicantResumeController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserCustomService userCustomService;
	
	@Autowired
	private ApplicantResumeRepository applicantResumeRepository;
	
	@Autowired
	private ResumeService applicantResumeService;
	
	@GetMapping("/resume/list")
	public String findAll(Model model) {
		model.addAttribute("applicantresumes", applicantResumeService.findAll());// 현재는 자바객체
		return "applicantResume";
	}
	
	
	@PostMapping("/applicantresume/create")
	public String resumeCreate(@AuthenticationPrincipal UserCustomerDetail userDetail,
			@RequestParam("file") MultipartFile file, String title, Education education , String wishAddress, 
			String job,String age, int salary, String experience,
			@RequestParam("zip") MultipartFile zip
			) throws IOException {
		file.getName();
		Path filePath = Paths.get(UserUtil.getResouecePath() + file.getOriginalFilename());
		
		System.out.println(file.getBytes());
		Files.write(filePath,file.getBytes());
		
		Path fileZip = Paths.get(UserUtil.getResouecePath()+zip.getOriginalFilename());
		Files.write(fileZip, zip.getBytes());
		
		User user = userDetail.getUser();
		
		
		
		ApplicantResume applicantresume = ApplicantResume.builder().title(title).
				user(user).job(job).age(age).salary(salary).education(education).
				experience(experience).wishAddress(wishAddress).
				mimeType(file.getContentType())
				.fileName(file.getOriginalFilename())
				.filePath("http://192.168.0.9:7000/image/"+file.getOriginalFilename())
				.zipmimeType(zip.getContentType())
				.zipfileName(zip.getOriginalFilename())
				.zipfilePath("/image"+zip.getOriginalFilename()).
				
				build();
		
		applicantResumeRepository.save(applicantresume);
		
		return "index"; 
	}
	
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
