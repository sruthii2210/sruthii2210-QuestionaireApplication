package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.Quiz;
import com.questionaire.exception.ServiceException;

public interface QuizService {

	Quiz addQuiz(Long id,String subCode,Quiz quiz) throws ServiceException;
	List<Quiz> getQuiz(Long id,String subCode) throws ServiceException;
	List<Quiz> getQuizBySubCode(String subCode) throws ServiceException;
	
	
}
