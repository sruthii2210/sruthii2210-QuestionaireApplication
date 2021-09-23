package com.questionaire.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.dto.Answer;
import com.questionaire.entity.AnswerEntity;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.AnswerService;

@RestController
@RequestMapping("/api/answer")
@CrossOrigin("http://localhost:4200")
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	@PostMapping("/{quesNo}")
	public ResponseEntity<Response> addAnswer(@PathVariable("quesNo") Integer quesNo, @RequestBody Answer answer) {
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			Long autoId = answerService.addAnswer(quesNo, answer);
			response.setData(autoId);
			response.setStatusText("Answer is added successfully!..");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

		} catch (ServiceException e) {
		
				response.setStatusCode(500);
				response.setStatusText(e.getMessage());
				responseBody = new ResponseEntity<>(response, new HttpHeaders(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return responseBody;
	}

	@GetMapping("/{quesNo}")
	public ResponseEntity<Response> getAnswer(@PathVariable("quesNo") Integer quesNo) {
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			AnswerEntity answer = answerService.getAnswer(quesNo);
			response.setData(answer);
			response.setStatusText("OK");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {
			String name = e.getClass().getName();
			System.out.println(name);
			if (name.equals("com.school.exception.ServiceException")) {
				response.setStatusCode(500);
				response.setStatusText("Internal Server Error");
				responseBody = new ResponseEntity<>(response, new HttpHeaders(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
			catch (QuestionNotFoundException e) {
				
					response.setStatusCode(404);
					response.setStatusText(e.getMessage());
					responseBody = new ResponseEntity<>(response, new HttpHeaders(),HttpStatus.NOT_FOUND);
		
		}
		return responseBody;
	}

	@PutMapping("/{autoId}/{quesNo}")
	public ResponseEntity<Response> updateAnswer(@PathVariable("autoId") Long autoId,
			@PathVariable("quesNo") Integer quesNo, @RequestBody Answer answer) {
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			Answer updatedAnswer = answerService.updateAnswer(autoId, quesNo, answer);
			response.setData(updatedAnswer);
			response.setStatusText("OK");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (ServiceException e) {
		
				response.setStatusCode(500);
				response.setStatusText("Internal Server Error");
				responseBody = new ResponseEntity<>(response, new HttpHeaders(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return responseBody;
	}
}
