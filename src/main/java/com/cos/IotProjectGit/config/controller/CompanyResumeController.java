package com.cos.IotProjectGit.config.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cos.IotProjectGit.model.ApplicantResume;
import com.cos.IotProjectGit.model.CompanyResume;
import com.cos.IotProjectGit.model.User;
import com.cos.IotProjectGit.model.code.Education;
import com.cos.IotProjectGit.repository.CompanyResumeRepository;
import com.cos.IotProjectGit.repository.UserRepository;
import com.cos.IotProjectGit.service.CompanyResumeService;
import com.cos.IotProjectGit.service.UserCustomService;
import com.cos.IotProjectGit.service.UserCustomerDetail;
import com.cos.IotProjectGit.util.UserUtil;

@Controller
public class CompanyResumeController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserCustomService userCustomService;

	@Autowired
	private CompanyResumeRepository companyResumeRepository;

	@Autowired
	private CompanyResumeService companyResumeService;

	@GetMapping("/resume/company/list")
	public String findAll(Model model) {
		model.addAttribute("companyresumes", companyResumeService.findAll());// 현재는 자바객체
		return "companyResume";
	}

	@PostMapping("/companyresume/create")
	public String resumeCreate(@AuthenticationPrincipal UserCustomerDetail userDetail,
			@RequestParam("file") MultipartFile file, String title, Education education, String wishAddress, String job,
			String age, int salary, String experience) throws IOException {

		Path filePath = Paths.get(UserUtil.getResouecePath() + file.getOriginalFilename());

		System.out.println(file.getBytes());
		Files.write(filePath, file.getBytes());

		User user = userDetail.getUser();

		CompanyResume companyResume = CompanyResume.builder().title(title).user(user).job(job).age(age).salary(salary)
				.education(education).experience(experience).wishAddress(wishAddress).mimeType(file.getContentType())
				.fileName(file.getOriginalFilename()).filePath("http://192.168.0.9:7000/image/" + file.getOriginalFilename())
				.build();

		companyResumeRepository.save(companyResume);

		return "close";
	}

	@GetMapping("/resume/company/{id}")
	public String detail(@PathVariable int id, Model model, @AuthenticationPrincipal UserCustomerDetail userDetail) {
		User user = userDetail.getUser();
		Optional<CompanyResume> resume = companyResumeService.detail(id);
		if (resume.isPresent()) {
			CompanyResume companyresume = resume.get();

			model.addAttribute("companyresume", companyresume);
			model.addAttribute("user", user);
			return "detail";
		} else {
			System.out.println("error");
			return "error";
		}

	}
}
