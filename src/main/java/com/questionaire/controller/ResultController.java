package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.dto.Result;
import com.questionaire.entity.ResultEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.exception.StudentIdNotFoundException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.ResultService;

@RestController
@RequestMapping("/api/result")
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	@PostMapping("/{rollNo}/{subCode}/{id}")
	public ResponseEntity<Response> addResult(@PathVariable("rollNo") Long rollNo,@PathVariable("subCode") String subCode,@PathVariable("id") Long id, @RequestBody Result result)
	{
		Long autoId=0l;
		ResponseEntity<Response> responseEntity = null;
		Response response = new Response();
		try {
			autoId=resultService.addResult(rollNo,subCode,id,result);
			response.setStatusText("Result is added to the student "+rollNo+" in quiz "+id+" for subject "+subCode+" ");
			 response.setStatusCode(200);
			 response.setData(autoId);
			 responseEntity=new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch ( NotFoundException e) {
			
			if(e instanceof QuizIdNotFoundException||e instanceof StudentIdNotFoundException||e instanceof SubjectNotFoundException)
			{
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseEntity = new ResponseEntity<>(response, new HttpHeaders(),
						HttpStatus.NOT_FOUND);
			}
		}
		catch(ServiceException e)
		{
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseEntity = new ResponseEntity<>(response, new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getResult(@PathVariable("id") Long id)
	{
		ResponseEntity<Response> responseEntity = null;
		Response response = new Response();
		List<ResultEntity>res;
		try {
			res= resultService.getResult(id);
			response.setStatusText("OK");
			 response.setStatusCode(200);
			 response.setData(res);
			 responseEntity=new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch ( NotFoundException e) {
			
			if(e instanceof QuizIdNotFoundException)
			{
				response.setStatusCode(404);
				response.setStatusText(e.getMessage());
				responseEntity = new ResponseEntity<>(response, new HttpHeaders(),
						HttpStatus.NOT_FOUND);
			}
		}
		catch(ServiceException e)
		{
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseEntity = new ResponseEntity<>(response, new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
