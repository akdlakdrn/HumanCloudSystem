package com.cos.IotProjectGit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.IotProjectGit.model.Addr;

public interface AddrRepository extends JpaRepository<Addr, Integer>{
	
	public List<Addr> findByBorough(String city);

}
