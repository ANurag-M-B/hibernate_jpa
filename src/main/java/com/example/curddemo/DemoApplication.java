package com.example.curddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.curddemo.dao.StudentDAO;
import com.example.curddemo.entity.Student;

@SpringBootApplication
//@ComponentScan({"com.example.dao"})
//@EntityScan("com.example.entity")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);
			
//			retriveStudentById(studentDAO);
//			getAllStudent(studentDAO);
//			getStudentsByLastName(studentDAO);
			
//			updateStudentById(studentDAO);
			deleteStudent(studentDAO);
		};
		
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentid = 7;
		Student myStudent = studentDAO.findById(studentid);
		boolean deleted = studentDAO.deleteStudentById(studentid);
		if (deleted) {
			System.out.println("Removed student : " + myStudent.toString());
		}
		
		
	}

	private void updateStudentById(StudentDAO studentDAO) {
		int studentid = 1;
		Student myStudent = studentDAO.findById(studentid);
		myStudent.setFirstName("Saby");
		
		studentDAO.update(myStudent);
		
		System.out.println("Updated Student : " + myStudent.toString());
		
	}

	private void getStudentsByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.getStudentsByLastName("MB");
		for(int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i).toString());
		}
		
	}

	private void getAllStudent(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();
		for(int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i).toString());
		}
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Murugan", "JK", "mjk@gmail.com");
		
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);
		
		System.out.println("Saved Student. Generated id: " + tempStudent.getId());
		
	}
	
	private void retriveStudentById(StudentDAO studentDAO) {
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Manog", "Jadgas", "ManogJ@gmail.com");
		
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);
		
		System.out.println("Saved Student. Generated id: " + tempStudent.getId());
		int id = tempStudent.getId();
		
		Student myStudent = studentDAO.findById(id);
		System.out.println(myStudent.toString());
	}

}
