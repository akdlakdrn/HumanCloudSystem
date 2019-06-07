package com.cos.IotProjectGit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.IotProjectGit.model.CompanyResume;
import com.cos.IotProjectGit.repository.CompanyResumeRepository;

@Service
public class CompanyResumeService {

	@Autowired
	CompanyResumeRepository resumeRepository;
	
	public List<CompanyResume> findAll(){
		return resumeRepository.findAll();
	}
	
	public Optional<CompanyResume> detail(int id) {
		return resumeRepository.findById(id);
	}
	
}
