package com.questionaire.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.QuizService;
import com.questionaire.util.ResponseUtil;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin("http://localhost:4200")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("/{id}/{code}")
	ResponseEntity<Response> addQuiz(@PathVariable("id") Long id, @PathVariable("code") String subCode,
			@RequestBody Quiz quiz) {
		ResponseEntity<Response> responseBody = null;
		try {
			Long quizId = quizService.addQuiz(id, subCode, quiz);
			responseBody = ResponseUtil.getResponse(200, "Quiz created Successfully!..", quizId);
		} catch (NotFoundException e) {

			if (e instanceof TeacherNotFoundException || e instanceof SubjectNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@GetMapping("/{id}/{code}")
	ResponseEntity<Response> getQuiz(@PathVariable("id") Long id, @PathVariable("code") String subCode) {
		ResponseEntity<Response> responseBody = null;
		List<QuizEntity> quiz;
		try {
			quiz = quizService.getQuiz(id, subCode);
			responseBody = ResponseUtil.getResponse(200, "Fetched Quiz Details..!", quiz);

		} catch (NotFoundException e) {

			if (e instanceof TeacherNotFoundException || e instanceof SubjectNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}
	
	@GetMapping("getQuiz/{id}/{code}")
	ResponseEntity<Response> getQuizByStaff(@PathVariable("id") Long id, @PathVariable("code") String subCode) {
		ResponseEntity<Response> responseBody = null;
		List<QuizEntity> quiz;
		try {
			quiz = quizService.getQuizByStaff(id, subCode);
			responseBody = ResponseUtil.getResponse(200, "Fetched Quiz Details..!", quiz);

		} catch (NotFoundException e) {

			if (e instanceof TeacherNotFoundException || e instanceof SubjectNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@GetMapping("/{code}")
	ResponseEntity<Response> getQuiz(@PathVariable("code") String subCode) {
		ResponseEntity<Response> responseBody = null;
		try {
			List<QuizEntity> quiz = quizService.getQuizBySubCode(subCode);
			responseBody = ResponseUtil.getResponse(200, "Fetched Quiz Details for " + subCode + " ..", quiz);

		} catch (NotFoundException e) {

			if (e instanceof SubjectNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}
	
	@GetMapping("quiz/getQuiz/quizId/{id}")
	ResponseEntity<Response> getQuizById(@PathVariable("id") Long id) {
		ResponseEntity<Response> responseBody = null;
		try {
			QuizEntity quiz = quizService.getQuizById(id);
			responseBody = ResponseUtil.getResponse(200, "Fetched Quiz Details", quiz);
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		} catch (NotFoundException e) {
			if ( e instanceof QuizIdNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		}
		return responseBody;
	}

	@GetMapping("/teacher/{teacherList}/{subjectList}")
	ResponseEntity<Response> getAllQuiz(@PathVariable("teacherList") List<Long>teacherList, @PathVariable("subjectList") List<String> subjectList) {
		ResponseEntity<Response> responseBody = null;
		List<List<QuizEntity>> quiz;
		try {
			quiz = quizService.getAllQuiz(teacherList, subjectList);
			responseBody = ResponseUtil.getResponse(200, "Fetched Quiz Details..!", quiz);

		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}
	
	
	@PutMapping("{id}/{quizId}/{code}")
	ResponseEntity<Response> updateQuiz(@PathVariable("id") Long id,@PathVariable("quizId") Long quizId, @PathVariable("code") String subCode,@RequestBody Quiz quiz) {
		ResponseEntity<Response> responseBody = null;
		try {
			QuizEntity updatedQuiz = quizService.updateQuiz(id,quizId,subCode, quiz);
			responseBody = ResponseUtil.getResponse(200, "Quiz updated Successfully!..", updatedQuiz);
		} catch (NotFoundException e) {

			if (e instanceof TeacherNotFoundException || e instanceof SubjectNotFoundException) {
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}
}
