package com.questionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.dto.TeacherLogin;
import com.questionaire.entity.TeacherLoginEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.IdNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.TeacherLoginService;
import com.questionaire.util.ResponseUtil;

@RestController
@RequestMapping("/api/login")
public class TeacherLoginController {

	@Autowired
	private TeacherLoginService teacherLoginService;

	@PostMapping("/{id}")
	public ResponseEntity<Response> createLogin(@PathVariable("id") Long id, @RequestBody TeacherLogin login) {
		Long autoId = 0l;
		ResponseEntity<Response> responseBody = null;
		try {
			autoId = teacherLoginService.createLogin(id, login);
			responseBody = ResponseUtil.getResponse(200, "Login Details created Sucessfully!", autoId);
		} catch (NotFoundException e) {

			if (e instanceof TeacherNotFoundException) {
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
		TeacherLoginEntity teacher;
		try {
			teacher = teacherLoginService.getDetails(id);
			responseBody = ResponseUtil.getResponse(200, "Login Details fetched!", teacher);
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
	public ResponseEntity<Response> updateLogin(@PathVariable("autoId") Long id, @RequestBody TeacherLogin login) {
		ResponseEntity<Response> responseBody = null;
		try {
			TeacherLoginEntity teacher = teacherLoginService.updateLogin(id, login);
			responseBody = ResponseUtil.getResponse(200, "Login Details updated!", teacher);
		} catch (NotFoundException e) {
			if (e instanceof TeacherNotFoundException || e instanceof IdNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

}
