package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.questionaire.entity.Result;

public interface ResultService {

	ResponseEntity<String> addResult(Long rollNo,String subCode,Long id, Integer score,Result result);
	
	 List<Result> getResult(Long id);

}
