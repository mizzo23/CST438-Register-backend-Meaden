package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Optional;

import static com.cst438.test.utils.TestUtils.fromJsonString;
import static com.cst438.test.utils.TestUtils.asJsonString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cst438.domain.CourseRepository;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.controller.StudentController;
import com.cst438.domain.StudentRepository;
import com.cst438.service.GradebookService;
import com.fasterxml.jackson.databind.ObjectMapper;

//In a simulated environment, the only classes loaded, are the classes specified
//in the ContextConfuration arguments
@ContextConfiguration(classes = {StudentController.class, StudentDTO.class, Student.class})
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
public class TestStudentController {
	
	static final String URL = "http://localhost:8080";
	public static final int TEST_COURSE_ID = 40442;
	public static final String TEST_STUDENT_EMAIL = "test@csumb.edu";
	public static final String TEST_STUDENT_NAME  = "test";
	public static final int TEST_YEAR = 2021;
	public static final String TEST_SEMESTER = "Fall";

	@MockBean
	StudentRepository studentRepository;
	
	@Autowired
	private MockMvc mvc;
	@Test
	
	public void checkHold() throws Exception {
		
	  MockHttpServletResponse response = 
			  mvc.perform(get("/student/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		//Check if you got a new problem
		assertEquals(200, response.getStatus());
	  /*
	  Student student = new Student();
	  student.setEmail(TEST_STUDENT_EMAIL);
	  student.setName(TEST_STUDENT_NAME);
	  student.setStatusCode(0);
	  student.setStudent_id(1);
	  //given(courseRepository.findById(TEST_COURSE_ID)).willReturn(Optional.of(course));
	  given(studentRepository.findById(1)).willReturn(Optional.of(student));
	  */
		
/*		
	  StudentDTO s = 
				
				fromJsonString(response.getContentAsString(),StudentDTO.class);
		
		response = 
		  mvc.perform(post("/placeOrRemoveHold").content(asJsonString(s)).contentType(MediaType.APPLICATION_JSON).
				                      accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		//Check if you post the result
		assertEquals(200, response.getStatus());
		
*/	  
		
		
	  // then do an http post request with body of courseDTO as JSON
	  /*
	  response = mvc.perform(
			MockMvcRequestBuilders
				.get("/student/1")
				.accept(MediaType.APPLICATION_JSON))
			  .andReturn().getResponse();
			
		// verify that return status = OK (value 200)
	  	assertEquals(200, response.getStatus());
	  	
	  	*/
	  
	  /*
	  	response = mvc.perform(
	  		MockMvcRequestBuilders
	  			.post("/student/holds")
	  			.accept(MediaType.APPLICATION_JSON)
	  			//.content(asJsonString(student_1))
	  			.contentType(MediaType.APPLICATION_JSON))
	  		.andReturn().getResponse();
	  				
	  	// verify that return status = OK (value 200)
	    assertEquals(200, response.getStatus());  
	  	//assertEquals(200, response.getStatus());
	  	//assertTrue(true);
	    
	    //verify(studentRepository).save(any(Student.class));
	  	Student s = 
				
				fromJsonString(response.getContentAsString(), Student.class);
		
		assertTrue(s.getStatusCode()== 1);
		*/
	  		
	}
	
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
