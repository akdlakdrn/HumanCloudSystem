package com.cos.IotProjectGit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.IotProjectGit.model.Skill;
import com.cos.IotProjectGit.model.User;

public interface SkillRepository extends JpaRepository<Skill, Integer>{
	
	public User findByUserId(String username);

}
