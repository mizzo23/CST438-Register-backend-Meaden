package com.cst438.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;


@RestController
public class StudentController {
	boolean status = true;
	@Autowired
	StudentRepository studentRepository;
	
	//@Autowired
	//Student st;
	

	@GetMapping("/student/{student_id}")
	public StudentDTO getStudent(@PathVariable("student_id") int id) {
		Student s = studentRepository.findById(id).get();
		
		if(s == null) {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, " 7777777 Student does not exist 777777777");
		}
		
		StudentDTO sto = new StudentDTO(s.getName(), s.getEmail(), s.getStatusCode(),s.getStatus());
		
		//TODO
		return sto;
	}
		
	
	/*
	public StudentDTO getStudent(@PathVariable("id") int id) {
		Student student = studentRepository.findById(id).get();
		if (student == null) {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "77777777 Student does not exist 77777777777");
		}
		//StudentDTO s = new StudentDTO();
		//copy student data to studentDTO
		return ;
	}
	*/
	
	@PostMapping("/student/holds")
	@Transactional       //Argument is JSON data for studentDTO. example: { "student_id": 1, "statusCode": -1}
	public StudentDTO placeOrRemoveHold( @RequestBody StudentDTO studentDTO  ) { 
		
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
			    status = false;
				throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, " Student does not exist");
		}
	    if( (student.getStatusCode() == studentDTO.statusCode) && studentDTO.statusCode == 0){
	    	status = false;
	    	throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, student.getName() + " Student has no holds");
		}
	    if( (student.getStatusCode() == studentDTO.statusCode) && studentDTO.statusCode == 1){
	    	status = false;
	    	throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, student.getName() + " Student already on hold");
		}
	    if( studentDTO.statusCode > 1){
	    	status = false;
	    	throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, student.getName() + " Invalid entry, values 1 or 0");
		}
	    if( studentDTO.statusCode < 0){
	    	status = false;
	    	throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, student.getName() + " Invalid entry, values 1 or 0");
		}
	    else {
	    	student.setStatusCode(studentDTO.statusCode);
	    	status = true;
	    	return studentDTO;
	    }
	    
	    //return status;
		
	}
	
}
