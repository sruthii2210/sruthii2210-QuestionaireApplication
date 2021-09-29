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

import com.questionaire.dto.Question;
import com.questionaire.entity.QuestionEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.QuestionService;
import com.questionaire.util.ResponseUtil;

@RestController
@RequestMapping("/api/question")
@CrossOrigin("http://localhost:4200")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping("/{id}")
	public ResponseEntity<Response> addQuestion(@PathVariable("id") Long id, @RequestBody Question question) {
		ResponseEntity<Response> responseBody = null;
		try {
			Integer quesNo = questionService.addQuestion(id, question);
			responseBody = ResponseUtil.getResponse(200, "Question is added...!", quesNo);

		} catch (NotFoundException e) {
			if (e instanceof QuizIdNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getQuestion(@PathVariable("id") Long id) {
		ResponseEntity<Response> responseBody = null;
		List<QuestionEntity> questions;
		try {
			questions = questionService.getQuestion(id);
			responseBody = ResponseUtil.getResponse(200, "Fetched Questions..", questions);
		} catch (NotFoundException e) {
			if (e instanceof QuizIdNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}
	
	@GetMapping("/questionCount/{id}")
	public ResponseEntity<Response> getQuestionCount(@PathVariable("id") Long id) {
		ResponseEntity<Response> responseBody = null;
		Long questionCount;
		try {
			questionCount = questionService.getQuestionCount(id);
			responseBody = ResponseUtil.getResponse(200, "Fetched QuestionCount..", questionCount);
		} catch (NotFoundException e) {
			if (e instanceof QuizIdNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@PutMapping("/{id}/{quesNo}")
	public ResponseEntity<Response> updateQuestion(@PathVariable("id") Long id, @PathVariable("quesNo") Integer quesNo,
			@RequestBody Question question) throws QuizIdNotFoundException {
		ResponseEntity<Response> responseBody = null;
		try {
			QuestionEntity questionEntity = questionService.updateQuestion(id, quesNo, question);
			responseBody = ResponseUtil.getResponse(200, "Question is updated...!", questionEntity);
		} catch (NotFoundException e) {
			if (e instanceof QuizIdNotFoundException || e instanceof QuestionNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}

		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@DeleteMapping("/{quesNo}")
	public ResponseEntity<Response> deleteQuestion(@PathVariable("quesNo") Integer quesNo)
			throws QuestionNotFoundException {
		ResponseEntity<Response> responseBody = null;
		try {
			String string = questionService.deleteQuestion(quesNo);
			responseBody = ResponseUtil.getResponse(200, "Question is deleted...!", string);
		} catch (NotFoundException e) {

			if (e instanceof QuestionNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}
}
