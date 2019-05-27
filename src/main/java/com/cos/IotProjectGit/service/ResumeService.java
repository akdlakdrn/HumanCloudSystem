package com.cos.IotProjectGit.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.IotProjectGit.model.ApplicantResume;
import com.cos.IotProjectGit.repository.ApplicantResumeRepository;

@Service
public class ResumeService {

	@Autowired
	ApplicantResumeRepository ResumeRepository;

	public List<ApplicantResume> findAll() {	
		return ResumeRepository.findAll();
	}
	
	public Optional<ApplicantResume> detail(int id) {
		return ResumeRepository.findById(id);
}
}
