package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.entity.Teacher;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.service.TeacherService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherServiceImpl;

	@PostMapping
	public ResponseEntity<Response> addTeacherDetails(@RequestBody Teacher teacherDetails) {
		Response response = new Response();
		ResponseEntity<Response> responseBody = null;
		try {
			Teacher t = teacherServiceImpl.addTeacherDetails(teacherDetails);
			response.setData(t);
			response.setStatusCode(200);
			response.setStatusText("Teacher Details added!");
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {

			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}

	@GetMapping
	public ResponseEntity<Response> getAllTeacherDetails() {
		Response response = new Response();
		ResponseEntity<Response> responseBody = null;
		List<Teacher> teacherList;
		try {
			 teacherList = teacherServiceImpl.getAllTeacherDetails();
			response.setData(teacherList);
			response.setStatusCode(200);
			response.setStatusText("Teacher Details fetchedd!");
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> updateTeacherDetails(@PathVariable("id") Long id,
			@RequestBody Teacher teacherDetails) {
		Response response = new Response();
		ResponseEntity<Response> responseBody = null;
		try {
			Teacher updatedTeacherDetails = teacherServiceImpl.updateTeacherDetails(id, teacherDetails);
			response.setData(updatedTeacherDetails);
			response.setStatusCode(200);
			response.setStatusText("Teacher Details updated!");
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}

	@DeleteMapping("/deleteTeacher/{id}")
	public ResponseEntity<String> deleteTeacherDetails(@PathVariable("id") Long id) {
		return teacherServiceImpl.deleteTeacherDetails(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getParticularTeacherDetails(@PathVariable("id") Long id) {
		Response response = new Response();
		ResponseEntity<Response> responseBody = null;
		try {
			Teacher teacher = teacherServiceImpl.getParticularTeacherDetails(id);
			response.setData(teacher);
			response.setStatusCode(200);
			response.setStatusText("Teacher Detail fetched!");
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
}
