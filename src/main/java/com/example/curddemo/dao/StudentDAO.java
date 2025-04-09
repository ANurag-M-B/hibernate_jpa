package com.example.curddemo.dao;

import java.util.List;

import com.example.curddemo.entity.Student;

public interface StudentDAO {
	
	void save (Student theStudent);
	
	Student findById(Integer id);

	List<Student> findAll();
	
	List<Student> getStudentsByLastName(String lastName);
	
	void update(Student student);
	
	boolean deleteStudentById(Integer id);
}
