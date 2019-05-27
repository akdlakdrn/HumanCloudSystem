package com.cos.IotProjectGit.config.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.IotProjectGit.model.Skill;
import com.cos.IotProjectGit.model.User;
import com.cos.IotProjectGit.repository.SkillRepository;
import com.cos.IotProjectGit.service.UserCustomerDetail;

@Controller
public class SkillController {
	
	@Autowired
	private SkillRepository skillrepository;
	
	@GetMapping("skill/{userId}")
	public  String Skill(@AuthenticationPrincipal UserCustomerDetail userDetail,@PathVariable int userId) {
		return "skill";
	}
	
	@PostMapping("skill/create")
	public @ResponseBody Skill skillCreate(@AuthenticationPrincipal UserCustomerDetail userDetail,
			String role,String skill) throws IOException {
		
		User user = userDetail.getUser();
		
		Skill createskill = Skill.builder().user(user).role(role).skill(skill).build();
		
	    skillrepository.save(createskill);
		
		return createskill; 

}
}
