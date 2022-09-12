package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.Enrollment;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import com.cst438.service.GradebookService;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	/*
	
	ScheduleDTO.CourseDTO result = createCourseDTO(savedEnrollment);
	 
	private ScheduleDTO.CourseDTO createCourseDTO(Enrollment e) {
		ScheduleDTO.CourseDTO courseDTO = new ScheduleDTO.CourseDTO();
		Course c = e.getCourse();
		courseDTO.id =e.getEnrollment_id();
		courseDTO.building = c.getBuilding();
		courseDTO.course_id = c.getCourse_id();
		courseDTO.endDate = c.getEnd().toString();
		courseDTO.instructor = c.getInstructor();
		courseDTO.room = c.getRoom();
		courseDTO.section = c.getSection();
		courseDTO.startDate = c.getStart().toString();
		courseDTO.times = c.getTimes();
		courseDTO.title = c.getTitle();
		courseDTO.grade = e.getCourseGrade();
		return courseDTO;
	}*/
	
/*
	@PostMapping("/student")
	@Transactional
	public StudentDTO createNewStudent( @RequestBody StudentDTO  ) { 

		String student_email = "test@csumb.edu";   // student's email 
		
		Student student = studentRepository.findByEmail(student_email);
		
		// student.status
		// = 0  ok to register
		// != 0 hold on registration.  student.status may have reason for hold.
		
		
		if (student== null) {
			// TODO check that today's date is not past add deadline for the course.
			student = create
			
			return result;
		} else {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student Exists.");
		}
		
	}
*/
}
