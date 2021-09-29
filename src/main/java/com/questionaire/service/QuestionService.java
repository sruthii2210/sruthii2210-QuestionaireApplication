package com.questionaire.service;

import java.util.List;

import com.questionaire.dto.Question;
import com.questionaire.entity.QuestionEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;

public interface QuestionService {

	Integer addQuestion(Long id, Question question) throws ServiceException, NotFoundException;

	List<QuestionEntity> getQuestion(Long id) throws ServiceException, NotFoundException;

	QuestionEntity updateQuestion(Long id, Integer quesNo, Question question)
			throws ServiceException, NotFoundException;

	String deleteQuestion(Integer quesNo) throws ServiceException, NotFoundException;

	Long getQuestionCount(Long id) throws NotFoundException, ServiceException;

}
