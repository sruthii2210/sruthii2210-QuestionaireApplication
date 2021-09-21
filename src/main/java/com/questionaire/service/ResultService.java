package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.questionaire.entity.Result;
import com.questionaire.exception.ServiceException;

public interface ResultService {

	Result addResult(Long rollNo,String subCode,Long id, Integer score,Result result) throws ServiceException;
	
	 List<Result> getResult(Long id) throws ServiceException;

}
