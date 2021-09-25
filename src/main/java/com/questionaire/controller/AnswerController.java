package com.questionaire.controller;

import javax.validation.Valid;

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

import com.questionaire.dto.Answer;
import com.questionaire.entity.AnswerEntity;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.AnswerService;
import com.questionaire.util.ResponseUtil;

@RestController
@RequestMapping("/api/answer")
@CrossOrigin("http://localhost:4200")
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	@PostMapping("/{quesNo}")
	public ResponseEntity<Response> addAnswer(@Valid @PathVariable("quesNo") Integer quesNo, @RequestBody Answer answer) {
		ResponseEntity<Response> responseBody = null;
		try {
			Long autoId = answerService.addAnswer(quesNo, answer);
//			response.setData(autoId);
//			response.setStatusText("Answer is added successfully!..");
//			response.setStatusCode(200);
			responseBody = ResponseUtil.getResponse(200, "Answer is added successfully!..", autoId);

		} catch ( NotFoundException e) {

			if (e instanceof QuestionNotFoundException) 
			{
//				response.setStatusText(e.getMessage());
//				response.setStatusCode(404);
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		}
			catch(ServiceException e)
			{
//				response.setStatusCode(500);
//				response.setStatusText(e.getMessage());
				responseBody = ResponseUtil.getResponse(500, e.getMessage());
			}
		
		return responseBody;
	}

	@GetMapping("/{quesNo}")
	public ResponseEntity<Response> getAnswer(@PathVariable("quesNo") Integer quesNo) {
		ResponseEntity<Response> responseBody = null;
		try {
			AnswerEntity answer = answerService.getAnswer(quesNo);
//			response.setData(answer);
//			response.setStatusText("OK");
//			response.setStatusCode(200);
			responseBody = ResponseUtil.getResponse(200, "Fetched options for quesNo "+quesNo, answer);
		
		} catch ( NotFoundException e) {

			if(e instanceof QuestionNotFoundException)
			{
//			response.setStatusCode(404);
//			response.setStatusText(e.getMessage());
			responseBody = ResponseUtil.getResponse(404, e.getMessage());
			}
		}
		catch(ServiceException e)
		{
//			response.setStatusCode(500);
//			response.setStatusText(e.getMessage());
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}

	@PutMapping("/{autoId}/{quesNo}")
	public ResponseEntity<Response> updateAnswer(@Valid @PathVariable("autoId") Long autoId,
			@PathVariable("quesNo") Integer quesNo, @RequestBody Answer answer) {
		ResponseEntity<Response> responseBody = null;
		try {
			AnswerEntity updatedAnswer = answerService.updateAnswer(autoId, quesNo, answer);
//			response.setData(updatedAnswer);
//			response.setStatusText("OK");
//			response.setStatusCode(200);
			responseBody = ResponseUtil.getResponse(200, "Updated answer!..", updatedAnswer);
		} catch (NotFoundException e) {

			if(e instanceof QuestionNotFoundException)
			{
//			response.setStatusCode(404);
//			response.setStatusText(e.getMessage());
				responseBody = ResponseUtil.getResponse(404, e.getMessage());
		}
		}
		catch(ServiceException e)
		{
//			response.setStatusCode(500);
//			response.setStatusText(e.getMessage());
			responseBody = ResponseUtil.getResponse(500, e.getMessage());
		}
		return responseBody;
	}
}
