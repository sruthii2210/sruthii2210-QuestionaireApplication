package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.entity.Result;
import com.questionaire.exception.DatabaseException;

public interface ResultRepository {

	Result addResult(Long rollNo,String subCode,Long id,Integer score,Result result) throws DatabaseException;
	
	List<Result> getResult(Long id) throws DatabaseException;
	
}
