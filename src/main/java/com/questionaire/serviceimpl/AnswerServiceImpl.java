package com.questionaire.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.questionaire.dto.Answer;
import com.questionaire.entity.AnswerEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.AnswerRepository;
import com.questionaire.repository.QuestionRepository;
import com.questionaire.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Long addAnswer(Integer quesNo, Answer answer) throws ServiceException, NotFoundException {
		try {
			questionRepository.checkQuestion(quesNo);
			return answerRepository.addAnswer(quesNo, answer);
		} 
		catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public AnswerEntity getAnswer(Integer quesNo) throws ServiceException, NotFoundException {
		try {
			questionRepository.checkQuestion(quesNo);
			return answerRepository.getAnswer(quesNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public AnswerEntity updateAnswer(Long autoId, Integer quesNo, Answer answer)
			throws ServiceException, NotFoundException {
		try {
			questionRepository.checkQuestion(quesNo);
			return answerRepository.updateAnswer(autoId, quesNo, answer);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
