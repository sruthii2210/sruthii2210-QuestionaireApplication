package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.QuizRepository;
import com.questionaire.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService{

	@Autowired
	private QuizRepository quizRepository;
	
	@Override
	public Long addQuiz(Long id,String subCode, Quiz quiz) throws ServiceException {
		try {
			return quizRepository.addQuiz(id,subCode,quiz);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<QuizEntity> getQuiz(Long id,String subCode) throws ServiceException {
		
		try {
			List<QuizEntity> quiz = quizRepository.getQuiz(id,subCode);
			return quiz;
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public List<QuizEntity> getQuizBySubCode(String subCode) throws ServiceException {
		List<QuizEntity> quiz;
		try {
			quiz = quizRepository.getQuizBySubCode(subCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		return quiz;
	}

}
