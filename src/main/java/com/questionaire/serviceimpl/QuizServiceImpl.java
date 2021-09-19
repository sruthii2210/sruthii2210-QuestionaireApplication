package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionaire.entity.Quiz;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.QuizRepository;
import com.questionaire.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService{

	@Autowired
	private QuizRepository quizRepository;
	
	@Override
	public Quiz addQuiz(Long id,String subCode, Quiz quiz) throws ServiceException {
		try {
			return quizRepository.addQuiz(id,subCode,quiz);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Quiz> getQuiz(Long id,String subCode) throws ServiceException {
		
		try {
			List<Quiz> quiz = quizRepository.getQuiz(id,subCode);
			return quiz;
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public List<Quiz> getQuizBySubCode(String subCode) throws ServiceException {
		List<Quiz> quiz;
		try {
			quiz = quizRepository.getQuizBySubCode(subCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		return quiz;
	}

}
