package com.cos.IotProjectGit.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.IotProjectGit.model.Addr;
import com.cos.IotProjectGit.repository.AddrRepository;

@Controller
public class AddrController {
	
	@Autowired
	private AddrRepository addrRepository;
	
	@PostMapping("/addr/{city}")
	public @ResponseBody List<Addr> addrCall(@PathVariable String city){
		
		List<Addr> listCity = addrRepository.findByBorough(city);
		listCity.get(1).getBorough();
		System.out.println(listCity);
		return listCity;
	}

}
