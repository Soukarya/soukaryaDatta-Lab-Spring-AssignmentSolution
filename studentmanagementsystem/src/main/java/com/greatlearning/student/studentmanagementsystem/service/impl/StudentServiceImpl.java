package com.greatlearning.student.studentmanagementsystem.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.student.studentmanagementsystem.entity.Student;
import com.greatlearning.student.studentmanagementsystem.repository.StudentRepository;
import com.greatlearning.student.studentmanagementsystem.service.StudentService;

import lombok.Data;

@Data
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	//readById
	public Student findById(int id) {
		return studentRepository.findById(id).get();
	}

	@Override
	//readAll
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	@Transactional
	//delete
	public void deleteById(int id) {
		studentRepository.deleteById(id);
	}

	@Override
	public List<Student> searchBy(String firstName, String course) {
		List<Student> students = studentRepository.findByFirstNameAndCourse(firstName, course);
		return students;
	}

}
