package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.entity.Quiz;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin("http://localhost:4200")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("/{id}/{subCode}")
	ResponseEntity<Response> addQuiz(@PathVariable("id") Long id, @PathVariable("subCode") String subCode,
			@RequestBody Quiz quiz) {
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			Quiz q = quizService.addQuiz(id, subCode, quiz);
			response.setData(q);
			response.setStatusText("OK");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {

			
				response.setStatusCode(500);
				response.setStatusText("Internal Server Error");
				responseBody = new ResponseEntity<Response>(response, new HttpHeaders(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return responseBody;

	}

	@GetMapping("/{id}/{subCode}")
	ResponseEntity<Response> getQuiz(@PathVariable("id") Long id, @PathVariable("subCode") String subCode) {
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		List<Quiz> q;
		try {
			q = quizService.getQuiz(id, subCode);
			response.setData(q);
			response.setStatusText("OK");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);

		} catch (ServiceException e) {
			String name = e.getClass().getName();
			if (name.equals("com.school.exception.ServiceException")) {
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseBody = new ResponseEntity<Response>(response, new HttpHeaders(),
						HttpStatus.NOT_FOUND);
			}
		}
		return responseBody;
	}

	@GetMapping("/{subCode}")
	ResponseEntity<Response> getQuiz(@PathVariable("subCode") String subCode) {
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			List<Quiz> q = quizService.getQuizBySubCode(subCode);
			response.setData(q);
			response.setStatusText("OK");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);

		} catch (ServiceException e) {
			String name = e.getClass().getName();
			if (name.equals("com.school.exception.ServiceException")) {
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseBody = new ResponseEntity<Response>(response, new HttpHeaders(),
						HttpStatus.NOT_FOUND);
			}
		}
		return responseBody;
	}
}
