package com.cst438.domain;


import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository <Course, Integer> {
	
	//Added 25 September
	//public Course findById(int id);
	
}