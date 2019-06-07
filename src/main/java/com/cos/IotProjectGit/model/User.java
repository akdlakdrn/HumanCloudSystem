package com.cos.IotProjectGit.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //기본키설정
	private int id;
	
	private String username;//유저이름
	private String password;//비밀번호
	private String name;//네임
	private String addr;//주소
	private String email;//이메일
	private String phone;//전화번호
	private String gender;//성별
	private int month; //지원자경력, 회사경력
	private String bio; //소개!!
	//@Column(name="number",columnDefinition = "String default 0")
	private String number; //사업자번호 or 주민번호
	//private Role role; //ROLE변경
	private String role; //Admin , Applicant 1, Company 2
	
	
	@CreationTimestamp
	private LocalDate createDate;
	@CreationTimestamp
	private LocalDate updateDate;
	
}
