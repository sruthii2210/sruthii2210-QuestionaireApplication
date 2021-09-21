package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.entity.TeacherLogin;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.TeacherLoginService;

@RestController
@RequestMapping("/api/login")
public class TeacherLoginController {

	@Autowired
	private TeacherLoginService teacherLoginService;
	
	@PostMapping("/{id}")
	public ResponseEntity<Response> createLogin(@PathVariable("id") Long id,@RequestBody TeacherLogin login)
	{
		Response response=new Response();
		ResponseEntity<Response>responseBody = null;
		try {
			TeacherLogin teacher=teacherLoginService.createLogin(id,login);
			response.setData(teacher);
			response.setStatusCode(200);
			response.setStatusText("Login Details created Sucessfully!");
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {

			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getDetails(@PathVariable("id") Long id)
	{
		Response response=new Response();
		ResponseEntity<Response>responseBody = null;
		List<TeacherLogin> teacher;
		try {
			teacher = teacherLoginService.getDetails(id);
			response.setData(teacher);
			response.setStatusCode(200);
			response.setStatusText("Login Details fetched!");
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {

			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
	
	@PutMapping("/{autoId}")
	public ResponseEntity<Response> updateLogin(@PathVariable("autoId") Long id,@RequestBody TeacherLogin login)
	{
		Response response=new Response();
		ResponseEntity<Response>responseBody = null;
		try {
			TeacherLogin teacher=teacherLoginService.updateLogin(id,login);
			response.setData(teacher);
			response.setStatusCode(200);
			response.setStatusText("Login Details updated!");
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		return responseBody;
		}
	
}
