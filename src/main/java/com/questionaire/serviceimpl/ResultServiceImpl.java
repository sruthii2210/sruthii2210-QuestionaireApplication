package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionaire.entity.Result;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.ResultRepository;
import com.questionaire.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService{

	@Autowired
	private ResultRepository resultRepository;
	
	@Override
	public Result addResult(Long rollNo, String subCode,Long id, Integer score,Result result) throws ServiceException {
		try{
			return resultRepository.addResult(rollNo,subCode,id,score,result);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Result> getResult(Long id) throws ServiceException {
		try
		{
		return resultRepository.getResult(id);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}

}
