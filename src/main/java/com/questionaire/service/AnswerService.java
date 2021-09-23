package com.questionaire.service;

import com.questionaire.dto.Answer;
import com.questionaire.entity.AnswerEntity;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.ServiceException;

public interface AnswerService {

	Long addAnswer(Integer quesNo,Answer answer) throws ServiceException;
	AnswerEntity getAnswer(Integer quesNo) throws ServiceException, QuestionNotFoundException;
	Answer updateAnswer( Long autoId,Integer quesNo,Answer answer) throws ServiceException;
	
	
}
