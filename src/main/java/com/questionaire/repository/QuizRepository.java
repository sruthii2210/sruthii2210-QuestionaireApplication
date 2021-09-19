package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.Quiz;
import com.questionaire.exception.DatabaseException;

public interface QuizRepository {

	Quiz addQuiz(Long id,String subCode,Quiz quiz) throws DatabaseException;
	List<Quiz> getQuiz(Long id,String subCode) throws DatabaseException;
	List<Quiz> getQuizBySubCode(String subCode) throws DatabaseException;
	
}
