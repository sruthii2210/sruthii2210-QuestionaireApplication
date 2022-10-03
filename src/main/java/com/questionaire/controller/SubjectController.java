package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.questionaire.dto.Subject;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.exception.ConstraintViolationException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.StandardNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.SubjectService;
import com.questionaire.util.ResponseUtil;

@RestController
@RequestMapping("/api/subject")
@CrossOrigin("http://localhost:4200")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@PostMapping("/{standard}")
	public ResponseEntity<Response> addSubject(@PathVariable("standard") String standard,
			@RequestBody Subject subject) {
		ResponseEntity<Response> responseBody = null;
		try {
			String subjectCode = subjectService.addSubject(standard, subject);
			responseBody = ResponseUtil.getResponse(200, "Subject added", subjectCode);
			System.out.println(responseBody);
		} catch (NotFoundException e) {
			if (e instanceof StandardNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
			if (e instanceof ConstraintViolationException) {
				responseBody = ResponseUtil.getResponse(422, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@GetMapping("/{standard}")
	public ResponseEntity<Response> getSubject(@PathVariable("standard") String standard) {
		ResponseEntity<Response> responseEntity = null;
		try {
			List<SubjectEntity> subjects = subjectService.getSubject(standard);
			responseEntity = ResponseUtil.getResponse(200, "Subjects fetched", subjects);
		} catch (NotFoundException e) {
			if (e instanceof StandardNotFoundException) {
				responseEntity = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseEntity = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseEntity;

	}
	
	@GetMapping("/{roomNo}/{subjectCodes}")
	public ResponseEntity<Response> getAllTeachers(@PathVariable("roomNo")Long roomNo, @PathVariable("subjectCodes")List<String> subjectCodes) {
		ResponseEntity<Response> responseEntity = null;
		try {
			List<Long> subjectList = subjectService.getAllTeachers(roomNo,subjectCodes);
			responseEntity = ResponseUtil.getResponse(200, "TeacherIds fetched", subjectList);
		} catch (ServiceException e) {
			responseEntity = ResponseUtil.getResponse(500, e.getMessage());
		} catch (NotFoundException e) {
			if (e instanceof RoomNoNotFoundException ) {
				responseEntity = ResponseUtil.getResponse(404, e.getMessage());
			}
		}
		return responseEntity;

	}

	@PutMapping("{standard}/{code}")
	public ResponseEntity<Response> updateSubject(@PathVariable("standard") String standard,
			@PathVariable("code") String subCode, @RequestBody Subject subject) {
		ResponseEntity<Response> responseEntity = null;
		try {
			SubjectEntity subjectEntity = subjectService.updateSubject(standard, subCode, subject);
			responseEntity = ResponseUtil.getResponse(200, "Subject Details updated!", subjectEntity);
		} catch (NotFoundException e) {
			if (e instanceof StandardNotFoundException || e instanceof SubjectNotFoundException) {
				responseEntity = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseEntity = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseEntity;
	}

	@DeleteMapping("/{subCode}")
	public ResponseEntity<Response> deleteSubject(@PathVariable("subCode") String subCode) {
		ResponseEntity<Response> responseEntity = null;
		try {
			String string = subjectService.deleteSubject(subCode);
			responseEntity = ResponseUtil.getResponse(200, "Subject is removed..!", string);
		} catch (NotFoundException e) {
			if (e instanceof SubjectNotFoundException) {
				responseEntity = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseEntity = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseEntity;
	}
}
