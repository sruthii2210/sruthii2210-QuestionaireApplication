package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.questionaire.util.ResponseUtil;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin("http://localhost:4200")
public class TeacherController {
	@Autowired
	private TeacherService teacherServiceImpl;

	@PostMapping
	public ResponseEntity<Response> addTeacherDetails(@RequestBody Teacher teacherDetails) {
		ResponseEntity<Response> responseBody = null;
		try {
			Long id = teacherServiceImpl.addTeacherDetails(teacherDetails);
			responseBody = ResponseUtil.getResponse(200, "Teacher Details added..!", id);
		} catch (ServiceException e) {

			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@GetMapping
	public ResponseEntity<Response> getAllTeacherDetails() {
		ResponseEntity<Response> responseBody = null;
		List<TeacherEntity> teacherList;
		try {
			teacherList = teacherServiceImpl.getAllTeacherDetails();
			responseBody = ResponseUtil.getResponse(200, "Teacher Details fetched.!", teacherList);
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> updateTeacherDetails(@PathVariable("id") Long id,
			@RequestBody Teacher teacherDetails) {
		ResponseEntity<Response> responseBody = null;
		try {
			TeacherEntity updatedTeacherDetails = teacherServiceImpl.updateTeacherDetails(id, teacherDetails);
			responseBody = ResponseUtil.getResponse(200, "Teacher Details updated..!", updatedTeacherDetails);
		} catch (NotFoundException e) {
			if (e instanceof TeacherNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteTeacherDetails(@PathVariable("id") Long id) {
		ResponseEntity<Response> responseBody = null;
		try {
			String string = teacherServiceImpl.deleteTeacherDetails(id);
			responseBody = ResponseUtil.getResponse(200, "Teacher Details deleted for id " + id + " !", string);
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
	public ResponseEntity<Response> getParticularTeacherDetails(@PathVariable("id") Long id) {
		ResponseEntity<Response> responseBody = null;
		try {
			TeacherEntity teacher = teacherServiceImpl.getParticularTeacherDetails(id);
			responseBody = ResponseUtil.getResponse(200, "Teacher Details fetched..!", teacher);
		} catch (NotFoundException e) {
			if (e instanceof TeacherNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}
}
