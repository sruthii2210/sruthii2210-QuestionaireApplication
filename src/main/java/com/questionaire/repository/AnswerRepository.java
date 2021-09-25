package com.questionaire.repository;

import com.questionaire.dto.Answer;
import com.questionaire.entity.AnswerEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;

public interface AnswerRepository {

	Long addAnswer(Integer quesNo, Answer answer) throws DatabaseException;

	AnswerEntity getAnswer(Integer quesNo) throws DatabaseException, QuestionNotFoundException;

	AnswerEntity updateAnswer(Long autoId, Integer quesNo, Answer answer) throws DatabaseException;

}
