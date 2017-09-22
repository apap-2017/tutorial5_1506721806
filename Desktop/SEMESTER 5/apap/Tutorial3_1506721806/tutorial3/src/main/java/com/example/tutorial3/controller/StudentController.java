package com.example.tutorial3.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.tutorial3.model.StudentModel;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.service.StudentService;

@Controller
public class StudentController {
	private final StudentService studentService;
	
	public StudentController() {
		studentService = new InMemoryStudentService();
	}
	
	@RequestMapping("/student/add")
	public String add(@RequestParam(value = "npm", required = true) String npm,
			@RequestParam(value = "name", required = true) String name, 
			@RequestParam(value = "gpa", required = true)double gpa) {
		StudentModel student = new StudentModel(npm, name, gpa);
		studentService.addStudent(student);
		return "add";
		
	}
	
	@RequestMapping(value = {"/student/delete", "/student/delete/{npm}"})
	public String delete(Model model, @PathVariable Optional<String> npm) {
		if(npm.isPresent()) {
			StudentModel s = studentService.selectStudent(npm.get());
			if(s == null) return "not_found";
			studentService.deleteStudent(npm.get());
			return "deleted";
		}
		
		return "not_found";
	}
	
	@RequestMapping(value = {"/student/view" ,"/student/view/{npm}"})
	public String view(Model model, @PathVariable Optional<String> npm) {
		 
		
		if(npm.isPresent()) {
			StudentModel student = studentService.selectStudent(npm.get());
			
			if(student == null) {
				return "not_found";
			} else {
				model.addAttribute("name",student.getName());
				model.addAttribute("npm",student.getNpm());
				model.addAttribute("gpa",student.getGpa());
				return "view";
			}
			
		} else {
			return "not_found";
		}
	}
	
	
	@RequestMapping("/student/viewall")
	public String viewAll (Model model) {
		List<StudentModel> students = studentService.selectAllStudents();
		model.addAttribute("students", students);
		return "viewall";
	}
}	
