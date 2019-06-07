package com.cos.IotProjectGit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.IotProjectGit.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByUsername(String username);
	
	
	//findByRoleOrderByCreateDateDesc(String role);
	//select * from user where role = 'company' order by createDate desc;
	
//	public User findById(int id);
	
	
	public User findByUsernameAndPassword(String username,String password);
}
