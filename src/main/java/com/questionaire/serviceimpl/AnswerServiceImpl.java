package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionaire.dto.Answer;
import com.questionaire.entity.AnswerEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.AnswerRepository;
import com.questionaire.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	private AnswerRepository answerRepository;
	@Override
	public Long addAnswer(Integer quesNo, Answer answer) throws ServiceException {
		try {
		return answerRepository.addAnswer(quesNo,answer);
		}
		catch (DatabaseException e) {
	    throw new ServiceException(e.getMessage());
	}
	}
	@Override
	public AnswerEntity getAnswer(Integer quesNo) throws ServiceException, QuestionNotFoundException {
		try {
		return answerRepository.getAnswer(quesNo);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());	
		}
	}
	@Override
	public Answer updateAnswer( Long autoId,Integer quesNo, Answer answer) throws ServiceException {
		try {
		return answerRepository.updateAnswer(autoId,quesNo,answer);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());	
		}
	}

}
