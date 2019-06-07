package com.cos.IotProjectGit.config.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.IotProjectGit.model.Joborthogonal;
import com.cos.IotProjectGit.repository.JobRepository;

@Controller
public class JobController {

	@Autowired
	private JobRepository jobRepository;

	@PostMapping("/job/{bigOrthogonal}")
	public @ResponseBody List<Smalljob> jobCall(@PathVariable String bigOrthogonal, HttpServletResponse response)
			throws IOException {
		List<Smalljob> smalljobs = new ArrayList<>();
		List<Joborthogonal> listbigOrthogonal = jobRepository.findByBigOrthogonal(bigOrthogonal);

		for (int i = 0; i < listbigOrthogonal.size(); i++) {
			Smalljob smalljob = new Smalljob();
			smalljob.setSmallJob(listbigOrthogonal.get(i).getSmallOrthogonal());
			smalljobs.add(smalljob);
		}

		return smalljobs;

//	      List<Joborthogonal> listsmallOrthogonal = jobRepository.findBysmallOrthogonal(bigOrthogonal);

	}

}
