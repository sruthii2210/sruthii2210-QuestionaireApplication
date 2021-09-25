package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import com.questionaire.entity.TeacherSubjectEntity;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.service.TeacherSubjectService;

@RestController
@RequestMapping("/api/teacherSubject")
@CrossOrigin("http://localhost:4200")
public class TeacherSubjectController {
	@Autowired
	private TeacherSubjectService teacherSubjectServiceImpl;

	@PostMapping("/teacher/{id}/subject/{subCode}")
	public ResponseEntity<Response> assignTeacherSubject(@PathVariable("id") Long teacherId,
			@PathVariable("subCode") String subCode, @RequestBody TeacherSubject teacherSubjectDetails) {
		Long autoId = 0l;
		Response response = new Response();
		ResponseEntity<Response> responseBody = null;
		try {
			autoId = teacherSubjectServiceImpl.assignTeacherSubject(teacherId, subCode, teacherSubjectDetails);
			response.setData(autoId);
			response.setStatusCode(200);
			response.setStatusText("Subjects assigned to staffs successfully!");
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch ( NotFoundException e) {

			if (e instanceof TeacherNotFoundException || e instanceof SubjectNotFoundException) {
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
			}
		}
		catch(ServiceException e)
		{
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response, new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}

	@PutMapping("/teacher/{id}/subject/{subCode}")
	public ResponseEntity<Response> updateTeacherSubjectAssign(@PathVariable("id") Long teacherId,
			@PathVariable("subCode") String subCode, @RequestBody TeacherSubject teacherSubjectDetails) {
		Response response = new Response();
		ResponseEntity<Response> responseBody = null;
		try {
			TeacherSubjectEntity teacher = teacherSubjectServiceImpl.updateTeacherSubjectAssign(teacherId, subCode,
					teacherSubjectDetails);
			response.setData(teacher);
			response.setStatusCode(200);
			response.setStatusText("Subjects assigned to staffs updated successfully!");
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch ( NotFoundException e) {
			if (e instanceof TeacherNotFoundException || e instanceof SubjectNotFoundException) {
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
			}
		}
		catch(ServiceException e)
		{
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response, new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}

	@DeleteMapping("/teacher/{id}/subject/{subCode}")
	public ResponseEntity<Response> deleteTeacherSubjectAssign(@PathVariable("id") Long teacherId,
			@PathVariable("subCode") String subCode) {
		Response response = new Response();
		ResponseEntity<Response> responseBody = null;
		try {
			String string = teacherSubjectServiceImpl.deleteTeacherSubjectAssign(teacherId, subCode);
			response.setData(string);
			response.setStatusCode(200);
			response.setStatusText("Subjects assigned to staffs deleted..");
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (NotFoundException e) {
			if (e instanceof TeacherNotFoundException || e instanceof SubjectNotFoundException) {
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
			}
		}
		catch(ServiceException e)
		{
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response, new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}

	@GetMapping("/teacher/{id}")
	public ResponseEntity<Response> getSubject(@PathVariable("id") Long teacherId) {
		Response response = new Response();
		ResponseEntity<Response> responseBody = null;
		try {
			List<TeacherSubjectModel> teacher = teacherSubjectServiceImpl.getSubject(teacherId);
			response.setData(teacher);
			response.setStatusCode(200);
			response.setStatusText("Subjects assigned to staffs deleted..");
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (NotFoundException e) {
			if (e instanceof TeacherNotFoundException) {
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
			}
		}
		catch(ServiceException e)
		{
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response, new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
}
