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

import com.questionaire.dto.Teacher;
import com.questionaire.entity.TeacherEntity;
import com.questionaire.exception.NotFoundException;
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
			Long id= teacherServiceImpl.addTeacherDetails(teacherDetails);
			response.setData(id);
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
		List<TeacherEntity> teacherList;
		try {
			 teacherList = teacherServiceImpl.getAllTeacherDetails();
			response.setData(teacherList);
			response.setStatusCode(200);
			response.setStatusText("Teacher Details fetchedd!");
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> updateTeacherDetails(@PathVariable("id") Long id,
			@RequestBody Teacher teacherDetails) {
		Response response = new Response();
		ResponseEntity<Response> responseBody = null;
		try {
			TeacherEntity updatedTeacherDetails = teacherServiceImpl.updateTeacherDetails(id, teacherDetails);
			response.setData(updatedTeacherDetails);
			response.setStatusCode(200);
			response.setStatusText("Teacher Details updated!");
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			if(e instanceof TeacherNotFoundException )
			{
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
			}
			}
		return responseBody;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteTeacherDetails(@PathVariable("id") Long id) {
		Response response = new Response();
		ResponseEntity<Response> responseBody = null;
		try {
			String string=teacherServiceImpl.deleteTeacherDetails(id);
			response.setData(string);
			response.setStatusCode(200);
			response.setStatusText("Teacher Details deleted for id "+id+" !");
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		}  catch (ServiceException | NotFoundException e) {
			if(e instanceof TeacherNotFoundException )
			{
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
			}
		}
		return responseBody;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getParticularTeacherDetails(@PathVariable("id") Long id) {
		Response response = new Response();
		ResponseEntity<Response> responseBody = null;
		try {
			TeacherEntity teacher = teacherServiceImpl.getParticularTeacherDetails(id);
			response.setData(teacher);
			response.setStatusCode(200);
			response.setStatusText("Teacher Detail fetched!");
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException | NotFoundException e)
		{
			if(e instanceof TeacherNotFoundException )
		   {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
			}
		}
		return responseBody;
	}
}
