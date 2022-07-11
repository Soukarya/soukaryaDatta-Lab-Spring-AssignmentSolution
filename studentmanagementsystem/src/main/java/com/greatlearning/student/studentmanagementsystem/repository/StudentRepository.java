package com.greatlearning.student.studentmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.student.studentmanagementsystem.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	List<Student> findByFirstNameAndCourse(String firstName, String course);
}
