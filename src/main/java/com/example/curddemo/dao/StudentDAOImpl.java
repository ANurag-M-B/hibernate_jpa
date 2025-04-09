package com.example.curddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.curddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentDAOImpl implements StudentDAO{
	
	// define field for entity manager
	private EntityManager entityManager;
	
	// inject entity manager using constructor injection
	@Autowired
	public StudentDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	// implement save method
	@Override
	@Transactional
	public void save(Student theStudent) {
		entityManager.persist(theStudent);
	}

	@Override
	public Student findById(Integer id) {
		Student myStudent = entityManager.find(Student.class, id);
		return myStudent;
	}

	@Override
	public List<Student> findAll() {
		TypedQuery<Student> theQuery = entityManager.createQuery("From Student order by lastName desc", Student.class);
		return theQuery.getResultList();
	}

	@Override
	public List<Student> getStudentsByLastName(String lastName) {
		TypedQuery<Student> getByLastNameQuery = entityManager.createQuery("Select s From Student s where lastName=:theData", Student.class);
		getByLastNameQuery.setParameter("theData", lastName);
		return getByLastNameQuery.getResultList();
	}

	@Override
	@Transactional
	public void update(Student student) {
		entityManager.merge(student);
	}

	@Override
	@Transactional
	public boolean deleteStudentById(Integer id) {
		boolean deleted = false;
		Student student = entityManager.find(Student.class, id);
		if(null != student) {
			entityManager.remove(student);
			deleted = true;
		} else {
			System.out.println("No Student with id " + id);
		}
		return deleted;
	}
	
}
