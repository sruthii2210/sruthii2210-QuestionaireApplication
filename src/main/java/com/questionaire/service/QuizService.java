package com.questionaire.service;

import java.util.List;

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;

public interface QuizService {

	Long addQuiz(Long id, String subCode, Quiz quiz) throws ServiceException, NotFoundException;

	List<QuizEntity> getQuiz(Long id, String subCode) throws ServiceException, NotFoundException;

	List<QuizEntity> getQuizBySubCode(String subCode) throws ServiceException, NotFoundException;

}
