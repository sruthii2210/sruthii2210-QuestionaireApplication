package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.Question;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.exception.ServiceException;

public interface QuestionService {

	Question addQuestion(Long id,Question question) throws ServiceException;
	List<Question> getQuestion(Long id) throws ServiceException;
	Question updateQuestion(Long id,Integer quesNo,Question question) throws  ServiceException;
	String deleteQuestion(Integer quesNo) throws  ServiceException;
	
	
	
	
}
