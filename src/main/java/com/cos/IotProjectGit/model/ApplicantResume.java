package com.cos.IotProjectGit.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.IotProjectGit.model.code.Education;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicantResume {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//타이틀 선언
	@Column(nullable = false, length = 100)//null값 금지
	private String title;
	//파일 관리를 위한 객체 선언
	private String mimeType;
	private String fileName;
	private String filePath;
	
	//폴더 관리를 위한 객체 선언
	private String zipmimeType; 
	private String zipfileName;
	private String zipfilePath;
	//희망주소
	private String wishAddress;
	//경력
	private String experience;
	//직종
	private String job;
	//나이
	private String age;
	//희망연봉
	private int salary;
	
	//학력 - ENUM
	private Education education; //1(고졸),2(전문대졸),3(대졸),4(석사),5(박사)
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({"id","username","password","createDate","updateDate"})
	private User user;
	
	@Transient
	@Builder.Default private List<ApplicantHistory> applicantHistory = new ArrayList<>(); //콤마로 구분해서 집어 넣음.
	
	@Transient
	@Builder.Default private List<Skill> skill = new ArrayList<>();
	
	@CreationTimestamp
	private LocalDate createDate;
	@CreationTimestamp
	private LocalDate updateDate;

}
