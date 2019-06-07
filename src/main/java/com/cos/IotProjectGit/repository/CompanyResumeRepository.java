package com.cos.IotProjectGit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.IotProjectGit.model.CompanyResume;

public interface CompanyResumeRepository extends JpaRepository<CompanyResume, Integer>{

	
	public List<CompanyResume> findByUserId(int userId);
}
