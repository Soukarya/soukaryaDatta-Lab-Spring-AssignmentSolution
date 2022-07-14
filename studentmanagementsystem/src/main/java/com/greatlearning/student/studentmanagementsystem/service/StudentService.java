package com.greatlearning.student.studentmanagementsystem.service;

import java.util.List;

import com.greatlearning.student.studentmanagementsystem.entity.Student;

//@Service
public interface StudentService {

	List<Student> findAll();
	Student findById(int id);
	void save(Student student);
	void deleteById(int id);
	public List<Student> searchBy(String firstName, String course);
}
