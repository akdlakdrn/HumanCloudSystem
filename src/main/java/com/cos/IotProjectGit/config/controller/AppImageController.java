package com.cos.IotProjectGit.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.IotProjectGit.model.ApplicantResume;
import com.cos.IotProjectGit.repository.ApplicantResumeRepository;

@Controller
public class AppImageController {

	@Autowired
	ApplicantResumeRepository applicantResumeRepository;

	@GetMapping("/app/image/{id}")
	public  String getfilePath(Model model, @PathVariable Integer id) {
		System.out.println("integer id" + id);
		System.out.println(applicantResumeRepository.findById(id).get());
		ApplicantResume applicantResume = applicantResumeRepository.findById(id).get();
		System.out.println("applicantresume" + applicantResume);
		applicantResume.getUser();
		System.out.println("111");
		applicantResume.getFilePath();
		System.out.println("222222");
		

		model.addAttribute("applicantResume", applicantResume);
		System.out.println("333");
		
		System.out.println("44");
		return "appimage";
	}

}
