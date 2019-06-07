package com.cos.IotProjectGit.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//회사 보유기술, 지원자 보유 기술 (구분은 Role)

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String role; //Admin, Applicant, Company
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	private String skill;
	
	@CreationTimestamp
	private LocalDate createDate;
	@CreationTimestamp
	private LocalDate updateDate;
}
