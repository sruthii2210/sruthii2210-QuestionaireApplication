package com.questionaire.service;

import com.questionaire.dto.Answer;
import com.questionaire.entity.AnswerEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;

public interface AnswerService {

	Long addAnswer(Integer quesNo, Answer answer) throws ServiceException, NotFoundException;

	AnswerEntity getAnswer(Integer quesNo) throws ServiceException, NotFoundException;

	AnswerEntity updateAnswer(Long autoId, Integer quesNo, Answer answer)
			throws ServiceException, NotFoundException;

}
