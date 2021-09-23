package com.questionaire.repository;

import java.util.List;

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuizIdNotFoundException;

public interface QuizRepository {

	Long addQuiz(Long id, String subCode, Quiz quiz) throws DatabaseException;

	void checkQuizBySubCode(String subCode) throws QuizIdNotFoundException;

	List<QuizEntity> getQuiz(Long id, String subCode) throws DatabaseException;

	List<QuizEntity> getQuizBySubCode(String subCode) throws DatabaseException;

}
