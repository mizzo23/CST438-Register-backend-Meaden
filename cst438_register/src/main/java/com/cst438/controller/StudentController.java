package com.cst438.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;


@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	//For Postman testing enter: http://localhost:8080/student/1   to return student with id of 1
	//This endpoint currently checks if a student exists, I need to add logic to add student if they don't
	//I also need the search to be based off of email address not student_id
	@GetMapping("/student/{student_id}")
	public StudentDTO getStudent(@PathVariable("student_id") int id) {
		Student s = studentRepository.findById(id).get();
		
		if(s == null) {
			
			//TODO add logic to add student here if they do not exist
			//throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student does not exist"); //testing
		}
		
		StudentDTO sto = new StudentDTO(s.getEmail(), s.getName(), s.getStatusCode(),s.getStatus());
		//StudentDTO sto = new StudentDTO();
		return sto;
	}
	
	@PostMapping("/student/add")
	public StudentDTO getStudent(@RequestBody StudentDTO sto) {
		Student s = studentRepository.findByEmail(sto.email); // (sto.email).get();
		
		if(s == null) {
			//TODO add logic to add student here if they do not exist
			//throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student does not exist"); //testing
			s = new Student();
			s.setEmail(sto.email);
			s.setName(sto.name);
			s.setStatus(sto.status);
			s.setStatusCode(sto.statusCode);
			studentRepository.save(s);
			//Student newStudent = new Student(sto.student_id, sto.name, sto.status, sto.statusCode);
			return sto;
		}
		else {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, s.getName() + " Student exists");
		}
	}
	
	
	@PostMapping("/student/holds")
	@Transactional       //Argument is JSON data for studentDTO. example: { "student_id": 1, "statusCode": -1}
	public void placeHold( @RequestBody StudentDTO studentDTO  ) { 
		
		String student_email = studentDTO.email;   // student's email 
		
		Student student = studentRepository.findByEmail(student_email);
		
		/* 
		   student.status
		   = 0  ok to register
		   = 1 hold on registration.  student.status may have reason for hold.
		
		   Checking for the following conditions, 
		   1. null 
		   2. same value is trying to be entered 
		   3. value > 1 or value < 0 
		 */
		if (student== null ){
				throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, " Student does not exist");
		}
	    if( (student.getStatusCode() == studentDTO.statusCode) && studentDTO.statusCode == 0){
	    	throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, student.getName() + " Student has no holds");
		}
	    if( (student.getStatusCode() == studentDTO.statusCode) && studentDTO.statusCode == 1){
	    	throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, student.getName() + " Student already on hold");
		}
	    if( studentDTO.statusCode > 1){
	    	throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, student.getName() + " Invalid entry, values 1 or 0");
		}
	    if( studentDTO.statusCode < 0){
	    	throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, student.getName() + " Invalid entry, values 1 or 0");
		}
	    else {
	    	student.setStatusCode(studentDTO.statusCode);
	    }
		
	}
	
}