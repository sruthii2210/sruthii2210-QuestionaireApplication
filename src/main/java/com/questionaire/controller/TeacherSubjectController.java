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

import com.questionaire.dto.TeacherSubject;
import com.questionaire.dto.TeacherSubjectModel;
import com.questionaire.entity.TeacherSubjectEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.service.TeacherSubjectService;
import com.questionaire.util.ResponseUtil;

@RestController
@RequestMapping("/api/teacherSubject")
@CrossOrigin("http://localhost:4200")
public class TeacherSubjectController {
	@Autowired
	private TeacherSubjectService teacherSubjectServiceImpl;

	@PostMapping("/teacher/{id}/subject/{code}/{roomNo}")
	public ResponseEntity<Response> assignTeacherSubject(@PathVariable("id") Long teacherId,
			@PathVariable("code") String subCode, @PathVariable("roomNo") Long roomNo,
			@RequestBody TeacherSubject teacherSubjectDetails) {
		Long autoId = 0l;
		ResponseEntity<Response> responseBody = null;
		try {
			autoId = teacherSubjectServiceImpl.assignTeacherSubject(teacherId, subCode, roomNo, teacherSubjectDetails);
			responseBody = ResponseUtil.getResponse(200, "Subjects assigned to staffs successfully!", autoId);
		} catch (NotFoundException e) {

			if (e instanceof TeacherNotFoundException || e instanceof SubjectNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@PutMapping("/teacher/{id}/subject/{code}/{roomNo}")
	public ResponseEntity<Response> updateTeacherSubjectAssign(@PathVariable("id") Long teacherId,
			@PathVariable("code") String subCode, @PathVariable("roomNo") Long roomNo,
			@RequestBody TeacherSubject teacherSubjectDetails) {
		ResponseEntity<Response> responseBody = null;
		try {
			TeacherSubjectEntity teacherSubject = teacherSubjectServiceImpl.updateTeacherSubjectAssign(teacherId,
					subCode, roomNo, teacherSubjectDetails);
			responseBody = ResponseUtil.getResponse(200, "Subjects assigned to staffs updated successfully!",
					teacherSubject);
		} catch (NotFoundException e) {
			if (e instanceof TeacherNotFoundException || e instanceof SubjectNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@DeleteMapping("/teacher/{id}/subject/{code}")
	public ResponseEntity<Response> deleteTeacherSubjectAssign(@PathVariable("id") Long teacherId,
			@PathVariable("code") String subCode) {
		ResponseEntity<Response> responseBody = null;
		try {
			String string = teacherSubjectServiceImpl.deleteTeacherSubjectAssign(teacherId, subCode);
			responseBody = ResponseUtil.getResponse(200, "Subjects assigned to staff deleted..", string);
		} catch (NotFoundException e) {
			if (e instanceof TeacherNotFoundException || e instanceof SubjectNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@GetMapping("/teacher/{id}")
	public ResponseEntity<Response> getSubject(@PathVariable("id") Long teacherId) {
		ResponseEntity<Response> responseBody = null;
		try {
			List<TeacherSubjectModel> teacher = teacherSubjectServiceImpl.getSubject(teacherId);
			responseBody = ResponseUtil.getResponse(200, "Fetched subjects assigned to staffs..", teacher);
		} catch (NotFoundException e) {
			if (e instanceof TeacherNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@GetMapping("/{roomNo}/{code}")
	public ResponseEntity<Response> getQuiz(@PathVariable("roomNo") Long roomNo, @PathVariable("code") String code) {
		ResponseEntity<Response> responseBody = null;
		try {
			TeacherSubjectModel quiz = teacherSubjectServiceImpl.getQuiz(roomNo, code);
			responseBody = ResponseUtil.getResponse(200, "Fetched details of quiz..", quiz);
		} catch (NotFoundException e) {
			if (e instanceof RoomNoNotFoundException || e instanceof SubjectNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}
	
	@GetMapping("/staff/{id}/{code}")
	public ResponseEntity<Response> getRoomNo(@PathVariable("id") Long id, @PathVariable("code") String code) {
		ResponseEntity<Response> responseBody = null;
		try {
			List<TeacherSubjectModel> quiz = teacherSubjectServiceImpl.getRoomNo(id, code);
			responseBody = ResponseUtil.getResponse(200, "Fetched details of quiz..", quiz);
		} catch (NotFoundException e) {
			if (e instanceof RoomNoNotFoundException || e instanceof SubjectNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}
}
