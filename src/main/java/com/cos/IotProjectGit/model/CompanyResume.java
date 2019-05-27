
package com.cos.IotProjectGit.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class CompanyResume {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String content;
	
	private String address;
	
	//파일 관리를 위한 객체 선언
	private String mimeType;
	private String fileName;
	private String filePath;
	
	//폴더 관리를 위한 객체 선언
	private String zipmimeType; 
	private String zipfileName;
	private String zipfilePath;
	
	@ManyToOne
	@JoinColumn(name="companyId")
	private User user;
	
	//학력 - ENUM
	private String education; //1(고졸),2(전문대졸),3(대졸),4(석사),5(박사)
	
	//구인 직업
	@ManyToOne
	@JoinColumn(name="jobId")
	private Job job;
	
	@CreationTimestamp
	private LocalDate createDate;
	@CreationTimestamp
	private LocalDate updateDate;
}
