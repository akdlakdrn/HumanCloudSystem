package com.cos.IotProjectGit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cos.IotProjectGit.model.Likes;

public interface LikesRepository extends JpaRepository<Likes, Integer>{

	public List<Likes> findById(int UserId);
	
	//커밋을 위해 적어주는 어노테이션 @Modifying, @Transactional
	
	@Transactional
	public void deleteByFromUserIdAndToUserId(int fromUser, int toUser);
}
