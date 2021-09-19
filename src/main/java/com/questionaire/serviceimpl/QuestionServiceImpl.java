package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionaire.entity.Question;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.QuestionRepository;
import com.questionaire.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	@Override
	public Question addQuestion(Long id, Question question) throws ServiceException {
		try {
			Question ques= questionRepository.addQuestion(id,question);
			return ques;
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public List<Question> getQuestion(Long id) throws ServiceException {
		try {
			List<Question>q=questionRepository.getQuestion(id);
			return q;
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Question updateQuestion(Long id, Integer quesNo, Question question) throws ServiceException{
		try {
			return questionRepository.updateQuestion(id,quesNo,question);
		}
		catch (DatabaseException  e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public String deleteQuestion(Integer quesNo) throws  ServiceException {
		try{
			 questionRepository.deleteQuestion(quesNo);
			String response="Question is removed Successfully!";
			 return response;
		}
		
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}

}
