package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.entity.Teacher;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.service.TeacherService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherServiceImpl;
	@PostMapping("/addTeacher")
	public ResponseEntity<String> addTeacherDetails(@RequestBody Teacher teacherDetails)
	{
		return teacherServiceImpl.addTeacherDetails(teacherDetails);
	}
	@GetMapping("/getAllTeachers")
	public ResponseEntity<List<Teacher>> getAllTeacherDetails()
	{
		return teacherServiceImpl.getAllTeacherDetails();
	}
	@PutMapping("/updateTeacher/{id}")
	public ResponseEntity<String> updateTeacherDetails(@PathVariable("id") Long id,@RequestBody Teacher teacherDetails) throws TeacherNotFoundException
	{
		return teacherServiceImpl.updateTeacherDetails(id,teacherDetails);
	}
	@DeleteMapping("/deleteTeacher/{id}")
	public ResponseEntity<String> deleteTeacherDetails(@PathVariable("id") Long id)
	{
		return teacherServiceImpl.deleteTeacherDetails(id);
	}
	@GetMapping("/getTeacher/{id}")
	public ResponseEntity<Teacher> getParticularTeacherDetails(@PathVariable("id") Long id)
	{
		return teacherServiceImpl.getParticularTeacherDetails(id);
	}
}
