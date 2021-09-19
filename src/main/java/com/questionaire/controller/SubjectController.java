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

import com.questionaire.entity.Subject;
import com.questionaire.service.SubjectService;



@RestController
@RequestMapping("/subject")
@CrossOrigin("http://localhost:4200")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@PostMapping("/{roomNo}/addSubject")
	public ResponseEntity<String> addSubject(@PathVariable("roomNo") Long roomNo,@RequestBody Subject subject)
	{
		return subjectService.addSubject(roomNo,subject);
	}
	
	@GetMapping("/{roomNo}/getSubject")
	public List<Subject> getSubject(@PathVariable("roomNo") Long roomNo)
	{
		List<Subject>subject=subjectService.getSubject(roomNo);
		return subject;
	}
	
	@PutMapping("{roomNo}/{subCode}/updateSubject")
	public ResponseEntity<String>updateSubject(@PathVariable("roomNo") Long roomNo,@PathVariable("subCode") String subCode,@RequestBody Subject subject)
	{
		return subjectService.updateSubject(roomNo,subCode,subject);
	}
	
	@DeleteMapping("/{subCode}/deleteSubject")
	public ResponseEntity<String>deleteSubject(@PathVariable("subCode") String subCode)
	{
		return subjectService.deleteSubject(subCode);
	}
}
