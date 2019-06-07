package com.cos.IotProjectGit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Addr {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int num;
	private String borough;
	private String city;

}
