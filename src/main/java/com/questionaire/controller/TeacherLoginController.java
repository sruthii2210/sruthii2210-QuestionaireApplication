package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.entity.TeacherLogin;
import com.questionaire.service.TeacherLoginService;

@RestController
@RequestMapping("/login")
public class TeacherLoginController {

	@Autowired
	private TeacherLoginService teacherLoginService;
	
	@PostMapping("/{id}/createLogin")
	public ResponseEntity<String> createLogin(@PathVariable("id") Long id,@RequestBody TeacherLogin login)
	{
		return teacherLoginService.createLogin(id,login);
	}
	
	@GetMapping("/{id}/getDetails")
	public List<TeacherLogin> getDetails(@PathVariable("id") Long id)
	{
		List<TeacherLogin> teacher=teacherLoginService.getDetails(id);
		return teacher;
	}
	
	@PutMapping("/{id}/updateLogin")
	public ResponseEntity<String> updateLogin(@PathVariable("id") Long id,@RequestBody TeacherLogin login)
	{
		return teacherLoginService.updateLogin(id,login);
	}
}
