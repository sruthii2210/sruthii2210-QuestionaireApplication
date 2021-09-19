package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.Answer;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;

public interface AnswerRepository {

	Answer addAnswer(Integer quesNo,Answer answer) throws DatabaseException;
	Answer getAnswer(Integer quesNo) throws DatabaseException, QuestionNotFoundException;
	Answer updateAnswer( Long autoId,Integer quesNo,Answer answer) throws DatabaseException;
	
}
