package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionaire.entity.Result;
import com.questionaire.repository.ResultRepository;
import com.questionaire.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService{

	@Autowired
	private ResultRepository resultRepository;
	
	@Override
	public ResponseEntity<String> addResult(Long rollNo, String subCode,Long id, Integer score,Result result) {
		return resultRepository.addResult(rollNo,subCode,id,score,result);
	}

	@Override
	public List<Result> getResult(Long id) {
		return resultRepository.getResult(id);
	}

}
