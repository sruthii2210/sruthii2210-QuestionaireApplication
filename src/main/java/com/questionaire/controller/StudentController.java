package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.entity.Student;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin("http://localhost:4200")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/{roomNo}/addStudent")
	public ResponseEntity<String> addStudent(@PathVariable("roomNo") Long roomNo,@RequestBody Student student)
	{
		return studentService.addStudent(roomNo,student);
	}
	@GetMapping("{roomNo}/getStudent")
	public List<Student> getStudent(@PathVariable("roomNo") Long roomNo)
	{
		List<Student> student;
		try {
			student = studentService.getStudent(roomNo);
		} catch (ServiceException e) {
			return (List<Student>) new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}
		return student;
	}
	@GetMapping("/getStudent/{rollNo}")
	public List<Student> getStudentById(@PathVariable("rollNo") Long rollNo)
	{
		List<Student> student=studentService.getStudentById(rollNo);
		return student;
	}
	@PutMapping("/{roomNo}/{rollNo}/updateStudent")
	public ResponseEntity<String> updateStudent(@PathVariable("roomNo") Long roomNo,@PathVariable("rollNo") Long rollNo,@RequestBody Student student)
	{
		return studentService.updateStudent(roomNo,rollNo,student);
	} 
	@DeleteMapping("/{rollNo}/deleteStudent")
	public ResponseEntity<String> deleteStudent(@PathVariable("rollNo") Long rollNo)
	{
		return studentService.deleteStudent(rollNo);
	} 
}
