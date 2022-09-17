package com.cst438;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
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
import com.fasterxml.jackson.databind.ObjectMapper;

//In a simulated environment, the only classes loaded, are the classes specified
//in the ContextConfuration arguments
@ContextConfiguration(classes = {StudentController.class, StudentDTO.class, Student.class})
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
public class TestStudentController {

	@MockBean
	StudentRepository studentRepository;
	
	public static final int TEST_STUDENT_ID = 0;
	public static final String TEST_STUDENT_NAME  = "test";
	public static final String TEST_STUDENT_EMAIL = "test@csumb.edu";
	public static final String TEST_STATUS = "null";
	public static final int TEST_STATUS_CODE = 1;
	public static final int TEST_STATUS_REMOVE_CODE = 0;
	
	@Autowired
	private MockMvc mvc;
	
	//Test to see if student can be placed on hold
	@Test
	public void checkHold() throws Exception {
		
	  StudentDTO sto = new StudentDTO(TEST_STUDENT_ID, TEST_STUDENT_NAME, TEST_STUDENT_EMAIL, TEST_STATUS, TEST_STATUS_CODE);
		
		
	  MockHttpServletResponse response = 
			  mvc.perform(MockMvcRequestBuilders.post("/student/holds").content(asJsonString(sto))
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andReturn().getResponse();
	          
	  assertEquals(200, response.getStatus());

	  		
	}
	//Test to see if a student can have a hold removed
	@Test
	public void checkRemoveHold() throws Exception {
		
	  StudentDTO sto = new StudentDTO(TEST_STUDENT_ID, TEST_STUDENT_NAME, TEST_STUDENT_EMAIL, TEST_STATUS, TEST_STATUS_REMOVE_CODE);
		
	  MockHttpServletResponse response = 
			  mvc.perform(MockMvcRequestBuilders.post("/student/holds").content(asJsonString(sto))
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andReturn().getResponse();
	          
	  assertEquals(200, response.getStatus());
 		
	}
	
	//Test to see if a student can be added
	@Test
	public void checkAddStudent() throws Exception{
		
		StudentDTO sto = new StudentDTO(TEST_STUDENT_ID, TEST_STUDENT_NAME, TEST_STUDENT_EMAIL, TEST_STATUS, TEST_STATUS_REMOVE_CODE);
		
		MockHttpServletResponse response = 
				  mvc.perform(MockMvcRequestBuilders.post("/student/add").content(asJsonString(sto))
		                .contentType(MediaType.APPLICATION_JSON)
		                .accept(MediaType.APPLICATION_JSON))
		                .andReturn().getResponse();
		          
		  assertEquals(200, response.getStatus());
	}
	
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
