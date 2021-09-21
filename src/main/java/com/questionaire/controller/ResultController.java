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

import com.questionaire.entity.Result;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.ResultService;

@RestController
@RequestMapping("/api/result")
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	@PostMapping("/{rollNo}/{subCode}/{id}/{score}")
	public ResponseEntity<Response> addResult(@PathVariable("rollNo") Long rollNo,@PathVariable("subCode") String subCode,@PathVariable("id") Long id, @PathVariable("score") Integer score,@RequestBody Result result)
	{
		ResponseEntity<Response> responseEntity = null;
		Response response = new Response();
		try {
			Result res=resultService.addResult(rollNo,subCode,id,score,result);
			response.setStatusText("OK");
			 response.setStatusCode(200);
			 response.setData(res);
			 responseEntity=new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			
			     response.setStatusCode(500);
			     response.setStatusText("Internal Server Error");
			     responseEntity = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
			 
		}
		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getResult(@PathVariable("id") Long id)
	{
		ResponseEntity<Response> responseEntity = null;
		Response response = new Response();
		List<Result>res;
		try {
			res= resultService.getResult(id);
			response.setStatusText("OK");
			 response.setStatusCode(200);
			 response.setData(res);
			 responseEntity=new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			String name = e.getClass().getName();
			  if(name.equals("com.questionaire.exception.ServiceException"))
			  {
			     response.setStatusCode(500);
			     response.setStatusText("Internal Server Error");
			     responseEntity = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
			  } 
		}
		return responseEntity;
	}
}
