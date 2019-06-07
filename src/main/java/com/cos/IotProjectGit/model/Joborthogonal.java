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

//insert select문!!

@Entity
@Data
public class Joborthogonal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String num;
	
	private String jobOrthogonal;
	
	
	private String bigOrthogonal;
	private String smallOrthogonal;
	
	
}
