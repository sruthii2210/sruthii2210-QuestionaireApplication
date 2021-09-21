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

import com.questionaire.entity.Student;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("http://localhost:4200")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/{roomNo}")
	public ResponseEntity<Response> addStudent(@PathVariable("roomNo") Long roomNo, @RequestBody Student student) {
		ResponseEntity<Response> responseEntity = null;
		Response response = new Response();
		try {
			Student stud = studentService.addStudent(roomNo, student);
			response.setStatusText("StudentDetails added");
			response.setStatusCode(200);
			response.setData(stud);
			responseEntity = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);

		} catch (ServiceException e) {
			String name = e.getClass().getName();
			response.setStatusCode(500);
			response.setStatusText("Internal Server Error");
			responseEntity = new ResponseEntity<Response>(response, new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping("/room/{roomNo}")
	public ResponseEntity<Response> getStudent(@PathVariable("roomNo") Long roomNo) {
		List<Student> student;
		ResponseEntity<Response> responseEntity = null;
		Response response = new Response();
		try {
			student = studentService.getStudent(roomNo);
			response.setStatusText("StudentDetails fetched!");
			response.setStatusCode(200);
			response.setData(student);
			responseEntity = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);

		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseEntity = new ResponseEntity<Response>(response, new HttpHeaders(),
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@GetMapping("/{rollNo}")
	public ResponseEntity<Response> getStudentById(@PathVariable("rollNo") Long rollNo) {
		ResponseEntity<Response> responseEntity = null;
		Response response = new Response();
		try {
			Student student = studentService.getStudentById(rollNo);
			response.setStatusText("StudentDetails fetched!");
			response.setStatusCode(200);
			response.setData(student);
			responseEntity = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);

		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseEntity = new ResponseEntity<Response>(response, new HttpHeaders(),
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;
		
	}

	@PutMapping("/{roomNo}/{rollNo}")
	public ResponseEntity<Response> updateStudent(@PathVariable("roomNo") Long roomNo,
			@PathVariable("rollNo") Long rollNo, @RequestBody Student student) {
		ResponseEntity<Response> responseEntity = null;
		Response response = new Response();
		try {
			Student stud =studentService.updateStudent(roomNo, rollNo, student);
			response.setStatusText("StudentDetails Updated!");
			response.setStatusCode(200);
			response.setData(stud);
			responseEntity = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseEntity = new ResponseEntity<Response>(response, new HttpHeaders(),
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@DeleteMapping("/{rollNo}")
	public ResponseEntity<Response> deleteStudent(@PathVariable("rollNo") Long rollNo) {
		ResponseEntity<Response> responseEntity = null;
		Response response = new Response();
		try {
			String string=studentService.deleteStudent(rollNo);
			response.setStatusText(string);
			response.setStatusCode(200);
			
			responseEntity = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);

		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseEntity = new ResponseEntity<Response>(response, new HttpHeaders(),
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
}
