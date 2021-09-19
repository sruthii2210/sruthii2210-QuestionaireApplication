package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.Question;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;

public interface QuestionRepository {

	Question addQuestion(Long id, Question question) throws DatabaseException;
	List<Question> getQuestion(Long id) throws DatabaseException;
	Question updateQuestion(Long id,Integer quesNo,Question question) throws DatabaseException;
	String deleteQuestion(Integer quesNo) throws DatabaseException;
	
	
}
