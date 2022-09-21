package com.cst438.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository; 
	
	/* To test add a student or place a hold, in Postman enter
	   in the format below: 

	{
    "name": "james",
    "email": "james@csumb.edu",
    "statusCode": 1,
    "status": null
	}
	
	*/
	//Endpoint to test add a student
	@PostMapping("/student")
	public StudentDTO getStudent(@RequestBody StudentDTO sto) {
		Student student = studentRepository.findByEmail(sto.email); // (sto.email).get();
		
		//If student does not exist, add them
		if(student == null) {
			//TODO add logic to add student here if they do not exist
			//throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student does not exist"); //testing
			student = new Student();
			student.setStudent_id(0);   //set to 0 because database will generate the key
			student.setEmail(sto.email);
			student.setName(sto.name);
			student.setStatus(sto.status);
			student.setStatusCode(sto.statusCode);
			studentRepository.save(student);
			sto.student_id = student.getStudent_id();//Professor mentioned adding this
			//Student newStudent = new Student(sto.student_id, sto.name, sto.status, sto.statusCode);
			return sto;
		}
		else {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, student.getName() + " Student exists");
		}
	}
	
	//Endpoint to add or remove a hold on a student
	@PostMapping("/student/holds")
	@Transactional       //Argument is JSON data for studentDTO. example: { "student_id": 1, "statusCode": -1}
	public StudentDTO placeHold( @RequestBody StudentDTO studentDTO  ) { 
	
		String student_email = studentDTO.email;   // student's email 
		
		Student student = studentRepository.findByEmail(student_email);
		
		//If the student doesn't exist, add them
		if (student==null) {
			studentRepository.save(student);
		}//If student already exists, set the status code with value from studentDTO
		else {
			student.setStatusCode(studentDTO.statusCode);
			studentRepository.save(student);
		}
		
    	return studentDTO;
	
	}
	
}
