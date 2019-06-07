package com.cos.IotProjectGit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.IotProjectGit.model.Joborthogonal;

public interface JobRepository extends JpaRepository<Joborthogonal, Integer>{

	
	public List<Joborthogonal> findByBigOrthogonal(String bigOrthogonal);
	
	
}
