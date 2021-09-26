package com.questionaire.controller;

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

import com.questionaire.dto.ClassDetails;
import com.questionaire.entity.ClassRoom;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.service.ClassService;
import com.questionaire.util.ResponseUtil;


@RestController
@RequestMapping("/api/class")
@CrossOrigin("http://localhost:4200")
public class ClassController {

	@Autowired
	private ClassService classServiceImp;

	@PostMapping
	public ResponseEntity<Response> addClass(@RequestBody ClassDetails classDetails) {
		ResponseEntity<Response> responseEntity = null;
		
		try {
		Long roomNo=classServiceImp.addClass(classDetails);
		 responseEntity=ResponseUtil.getResponse(200,"ClassDetails added..!",roomNo);
		}
		catch(ServiceException e)
		{
			responseEntity=ResponseUtil.getResponse(500,e.getMessage());
		}
		return responseEntity;
	}
	
	@GetMapping
	public ResponseEntity<Response> getClassDetails()
	{
		ResponseEntity<Response> responseEntity = null;
		List<ClassRoom> classDetails;
		try {
			classDetails = classServiceImp.getClassDetails();
			responseEntity=ResponseUtil.getResponse(200,"Fetched classDetails..!",classDetails);
			
		} catch(ServiceException e)
		{
			responseEntity=ResponseUtil.getResponse(500,e.getMessage());
		}
		return responseEntity;
	}
	
	@PutMapping("/{roomNo}")
	public ResponseEntity<Response> updateClass(@PathVariable("roomNo")Long roomNo,@RequestBody ClassDetails classDetails)
	{
		ResponseEntity<Response> responseEntity = null;
		
		try {	
			 ClassRoom updatedClass= classServiceImp.updateClass(roomNo,classDetails);
			 responseEntity=ResponseUtil.getResponse(200,"ClassDetails updated for roomNo "+roomNo+" !",updatedClass);
			
		} catch ( NotFoundException e) {
			  
			  if(e instanceof RoomNoNotFoundException)
			  {
					responseEntity=ResponseUtil.getResponse(404,e.getMessage());
			  } 
		
		} 
		catch(ServiceException e)
		{
			responseEntity=ResponseUtil.getResponse(500,e.getMessage());
		}
		
		return responseEntity;
	}
	
	@GetMapping("/{standard}/{section}")
	public ResponseEntity<Response> getClass(@PathVariable("standard") String standard,@PathVariable("section") String section)
	{
		ResponseEntity<Response> responseEntity = null;
		ClassRoom classDetails;
		try {
			classDetails = classServiceImp.getClass(standard,section);
			 responseEntity=ResponseUtil.getResponse(200,"Fetching ClassDetails...!",classDetails);
		} catch(ServiceException e)
		{
			responseEntity=ResponseUtil.getResponse(500,e.getMessage());
		}
		return responseEntity;
	}
		
	
}
