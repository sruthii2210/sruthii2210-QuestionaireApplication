package com.questionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.dto.StudentLogin;
import com.questionaire.entity.StudentLoginEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.StudentIdNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.service.StudentLoginService;
import com.questionaire.util.ResponseUtil;

@RestController
@RequestMapping("/api/studentlogin")
@CrossOrigin("http://localhost:4200")
public class StudentLoginController {
	
	@Autowired
	private StudentLoginService studentLoginService;
	@PostMapping("/{id}")
	public ResponseEntity<Response> createLogin(@PathVariable("id") Long id, @RequestBody StudentLogin login) {
		Long autoId = 0l;
		ResponseEntity<Response> responseBody = null;
		try {
			autoId = studentLoginService.createLogin(id, login);
			responseBody = ResponseUtil.getResponse(200, "Login Details created Sucessfully!", autoId);
		} catch (NotFoundException e) {

			if (e instanceof StudentIdNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getDetails(@PathVariable("id") Long id) {
		ResponseEntity<Response> responseBody = null;
		StudentLoginEntity student;
		try {
			student = studentLoginService.getDetails(id);
			responseBody = ResponseUtil.getResponse(200, "Login Details fetched!", student);
		} catch (NotFoundException e) {

			if (e instanceof TeacherNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@PutMapping("/{autoId}")
	public ResponseEntity<Response> updateLogin(@PathVariable("autoId") Long id, @RequestBody StudentLogin login) {
		ResponseEntity<Response> responseBody = null;
		try {
			StudentLoginEntity teacher = studentLoginService.updateLogin(id, login);
			responseBody = ResponseUtil.getResponse(200, "Login Details updated!", teacher);
		} catch (NotFoundException e) {
			if (e instanceof NotFoundException || e instanceof StudentIdNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}


}
