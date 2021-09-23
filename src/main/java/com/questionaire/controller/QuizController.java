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

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.exception.SubjectNotFoundException;
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
			Long quizId = quizService.addQuiz(id, subCode, quiz);
			response.setData(quizId);
			response.setStatusText("Quiz created Successfully!..");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {

			if(e instanceof TeacherNotFoundException|e instanceof SubjectNotFoundException)
			{
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseBody = new ResponseEntity<>(response, new HttpHeaders(),
						HttpStatus.NOT_FOUND);
			}
		}
		return responseBody;

	}

	@GetMapping("/{id}/{subCode}")
	ResponseEntity<Response> getQuiz(@PathVariable("id") Long id, @PathVariable("subCode") String subCode) {
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		List<QuizEntity> quiz;
		try {
			quiz = quizService.getQuiz(id, subCode);
			response.setData(quiz);
			response.setStatusText("Fetched Quiz Details..!");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

		} catch (ServiceException | NotFoundException e) {
			
			if (e instanceof TeacherNotFoundException|e instanceof SubjectNotFoundException ) {
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseBody = new ResponseEntity<>(response, new HttpHeaders(),
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
			List<QuizEntity> q = quizService.getQuizBySubCode(subCode);
			response.setData(q);
			response.setStatusText("Fetched Quiz Details for "+subCode+" ..");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

		} catch (ServiceException | NotFoundException e) {
			
			if (e instanceof SubjectNotFoundException) {
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseBody = new ResponseEntity<>(response, new HttpHeaders(),
						HttpStatus.NOT_FOUND);
			}
		}
		return responseBody;
	}
}
