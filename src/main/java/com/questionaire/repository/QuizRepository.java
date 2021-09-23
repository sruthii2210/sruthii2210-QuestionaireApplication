package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;
import com.questionaire.exception.DatabaseException;

public interface QuizRepository {

	Long addQuiz(Long id,String subCode,Quiz quiz) throws DatabaseException;
	List<QuizEntity> getQuiz(Long id,String subCode) throws DatabaseException;
	List<QuizEntity> getQuizBySubCode(String subCode) throws DatabaseException;
	
}
