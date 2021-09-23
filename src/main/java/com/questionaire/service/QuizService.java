package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;
import com.questionaire.exception.ServiceException;

public interface QuizService {

	Long addQuiz(Long id,String subCode,Quiz quiz) throws ServiceException;
	List<QuizEntity> getQuiz(Long id,String subCode) throws ServiceException;
	List<QuizEntity> getQuizBySubCode(String subCode) throws ServiceException;
	
	
}
