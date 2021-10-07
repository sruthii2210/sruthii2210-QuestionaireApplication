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

import com.questionaire.dto.Student;
import com.questionaire.entity.StudentEntity;
import com.questionaire.exception.ConstraintViolationException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.StudentIdNotFoundException;
import com.questionaire.service.StudentService;
import com.questionaire.util.ResponseUtil;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("http://localhost:4200")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/{roomNo}")
	public ResponseEntity<Response> addStudent(@PathVariable("roomNo") Long roomNo, @RequestBody Student student) {
		ResponseEntity<Response> responseEntity = null;
		try {
			Long studId = studentService.addStudent(roomNo, student);
			responseEntity = ResponseUtil.getResponse(200, "StudentDetails added", studId);

		} catch (NotFoundException e) {
			if (e instanceof RoomNoNotFoundException) {
				responseEntity = ResponseUtil.getResponse(404, e.getMessage());
			}
			if (e instanceof ConstraintViolationException) {
				responseEntity = ResponseUtil.getResponse(422, e.getMessage());
			}
		} catch (ServiceException e) {
			responseEntity = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseEntity;
	}

	@GetMapping("/room/{roomNo}")
	public ResponseEntity<Response> getStudent(@PathVariable("roomNo") Long roomNo) {
		List<StudentEntity> students;
		ResponseEntity<Response> responseEntity = null;
		try {
			students = studentService.getStudent(roomNo);
			responseEntity = ResponseUtil.getResponse(200, "StudentDetails fetched", students);

		} catch (NotFoundException e) {
			if (e instanceof RoomNoNotFoundException) {
				responseEntity = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseEntity = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseEntity;
	}

	@GetMapping("/{rollNo}")
	public ResponseEntity<Response> getStudentById(@PathVariable("rollNo") Long rollNo) {
		ResponseEntity<Response> responseEntity = null;
		try {
			StudentEntity student = studentService.getStudentById(rollNo);
			responseEntity = ResponseUtil.getResponse(200, "StudentDetail fetched ", student);

		} catch (NotFoundException e) {
			if (e instanceof StudentIdNotFoundException) {
				responseEntity = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseEntity = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseEntity;

	}

	
	@PutMapping("/{roomNo}/{rollNo}")
	public ResponseEntity<Response> updateStudent(@PathVariable("roomNo") Long roomNo,
			@PathVariable("rollNo") Long rollNo, @RequestBody Student student) {
		ResponseEntity<Response> responseEntity = null;
		try {
			StudentEntity updatedStudent = studentService.updateStudent(roomNo, rollNo, student);
			responseEntity = ResponseUtil.getResponse(200, "StudentDetails updated", updatedStudent);
		} catch (NotFoundException e) {
			if (e instanceof StudentIdNotFoundException || e instanceof RoomNoNotFoundException) {
				responseEntity = ResponseUtil.getResponse(400, e.getMessage());
			}
		} catch (ServiceException e) {
			responseEntity = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseEntity;
	}

	@DeleteMapping("/{rollNo}")
	public ResponseEntity<Response> deleteStudent(@PathVariable("rollNo") Long rollNo) {
		ResponseEntity<Response> responseEntity = null;
		try {
			String string = studentService.deleteStudent(rollNo);
			responseEntity = ResponseUtil.getResponse(200, "StudentDetail deleted", string);

		} catch (NotFoundException e) {
			if (e instanceof StudentIdNotFoundException) {
				responseEntity = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseEntity = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseEntity;
	}
}
