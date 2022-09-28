package com.cst438.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentDTO;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



public class GradebookServiceREST extends GradebookService {
	
	private RestTemplate restTemplate = new RestTemplate();

	@Value("${gradebook.url}")
	String gradebook_url;
	
	public GradebookServiceREST() {
		System.out.println("REST grade book service");
	}

	@Override
	public void enrollStudent(String student_email, String student_name, int course_id) {
		
		
		//TODO  complete this method in homework 4
		/*
		 * Send POST message to Gradebook backend using EnrollmentDTO.class
		 * 
		 */
		EnrollmentDTO enrollment = new EnrollmentDTO();
		enrollment.course_id = course_id;
		enrollment.studentEmail = student_email;
		enrollment.studentName = student_name;
		EnrollmentDTO response = restTemplate.postForObject( gradebook_url+"/enrollment", enrollment, EnrollmentDTO.class);
		System.out.println("Response from gradebook " + response);
		
		
	}

}
