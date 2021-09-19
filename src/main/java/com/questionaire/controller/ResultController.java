package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.entity.Result;
import com.questionaire.service.ResultService;

@RestController
@RequestMapping("/result")
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	@PostMapping("/{rollNo}/{subCode}/{id}/{score}/addResult")
	public ResponseEntity<String> addResult(@PathVariable("rollNo") Long rollNo,@PathVariable("subCode") String subCode,@PathVariable("id") Long id, @PathVariable("score") Integer score,@RequestBody Result result)
	{
		return resultService.addResult(rollNo,subCode,id,score,result);
	}
	
	@GetMapping("/{id}/getResult")
	public List<Result> getResult(@PathVariable("id") Long id)
	{
		return resultService.getResult(id);
	}
}
