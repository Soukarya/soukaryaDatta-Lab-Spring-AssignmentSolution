package com.greatlearning.student.studentmanagementsystem.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.student.studentmanagementsystem.entity.Student;
import com.greatlearning.student.studentmanagementsystem.service.StudentService;

import lombok.Data;

@Data
@Controller
@RequestMapping(path="/students")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String showHomePage() {
		return "index";
	}
	
	@RequestMapping(path="/list")
	public String listStudents(Model model) {
		List<Student> students = service.findAll();
		model.addAttribute("students", students);
		return "list-students";
	}
	
	@RequestMapping(path="/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		return "student-form";
	}
	
	@RequestMapping(path="/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int id, Model model) {
		
		Student student = service.findById(id);
		model.addAttribute("student", student);
		return "student-form";
	}
	
	@PostMapping(path="/save")
	public String save(@RequestParam("id") int id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,@RequestParam(name="course") String course, @RequestParam("country") String country) {
		Student student;
		if(id!=0) {
			student = service.findById(id);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setCourse(course);
			student.setCountry(country);
		}else {
			student = new Student(firstName, lastName,course,country);
		}
		service.save(student);
		return "redirect:/students/list";
	}
	
	@RequestMapping(path="/delete")
	public String delete(@RequestParam("studentId") int id) {
		service.deleteById(id);
		return "redirect:/students/list";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("firstname") String firstname, @RequestParam("course") String course,
			Model model) {
		if (firstname.trim().isEmpty() && course.trim().isEmpty()) {
			return "redirect:/students/list";
		} else {
			List<Student> students = service.searchBy(firstname, course);
			model.addAttribute("students", students);
			return "list-students";
		}
	}

	@RequestMapping("/403")
	public ModelAndView accessDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("errorMessage", "Hi, " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("errorMessage", "You do not have permission to access this page");
		}

		model.setViewName("403");
		return model;
	}
	
}
