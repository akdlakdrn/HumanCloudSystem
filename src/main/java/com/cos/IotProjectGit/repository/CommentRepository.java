package com.cos.IotProjectGit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.IotProjectGit.model.Comment;
import com.cos.IotProjectGit.model.User;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	public Optional<List<Comment>> findByToUserIdOrderByCreateDateDesc(int toUserId);

}
