package com.cos.IotProjectGit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.IotProjectGit.model.ApplicantResume;

public interface ApplicantResumeRepository extends JpaRepository<ApplicantResume, Integer>{
	
	
	
	public List<ApplicantResume> findByUserId(int userId);
	
	//public Optional<ApplicantResume> findById(int id);

}
