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

import com.questionaire.dto.Subject;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.SubjectService;



@RestController
@RequestMapping("/api/subject")
@CrossOrigin("http://localhost:4200")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@PostMapping("/{roomNo}")
	public ResponseEntity<Response> addSubject(@PathVariable("roomNo") Long roomNo,@RequestBody Subject subject)
	{
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			String subCode=subjectService.addSubject(roomNo,subject);
			response.setData(subCode);
			response.setStatusText("Subject added");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch ( NotFoundException e) {
			if(e instanceof RoomNoNotFoundException )
			{
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response, new HttpHeaders(),
					HttpStatus.NOT_FOUND);
			}
		}
		catch(ServiceException e)
		{
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response, new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
	
	@GetMapping("/{roomNo}")
	public ResponseEntity<Response> getSubject(@PathVariable("roomNo") Long roomNo)
	{
		ResponseEntity<Response> responseEntity = null;
		Response response = new Response();
		try {
			List<SubjectEntity>subject=subjectService.getSubject(roomNo);
			response.setData(subject);
			response.setStatusText("Subject Details Fetched");
			response.setStatusCode(200);
			responseEntity = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (NotFoundException e) {
			if(e instanceof RoomNoNotFoundException)
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
	
	@PutMapping("{roomNo}/{subCode}")
	public ResponseEntity<Response>updateSubject(@PathVariable("roomNo") Long roomNo,@PathVariable("subCode") String subCode,@RequestBody Subject subject)
	{
		ResponseEntity<Response>responseEntity=null;
		Response response=new Response();
		try {
			SubjectEntity sub=subjectService.updateSubject(roomNo,subCode,subject);
			response.setData(sub);
			response.setStatusText("Subject Details updated!");
			response.setStatusCode(200);
			responseEntity = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (NotFoundException e) {
			if(e instanceof RoomNoNotFoundException||e instanceof SubjectNotFoundException)
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
	
	@DeleteMapping("/{subCode}")
	public ResponseEntity<Response>deleteSubject(@PathVariable("subCode") String subCode)
	{
		ResponseEntity<Response>responseEntity=null;
		Response response=new Response();
		try {
			String string=subjectService.deleteSubject(subCode);
			response.setStatusText(string);
			response.setStatusCode(200);
			responseEntity = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (NotFoundException e) {
			if(e instanceof SubjectNotFoundException)
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
