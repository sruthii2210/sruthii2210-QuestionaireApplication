package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.entity.Result;

public interface ResultRepository {

	ResponseEntity<String> addResult(Long rollNo,String subCode,Long id,Integer score,Result result);
	
	List<Result> getResult(Long id);
	
}
