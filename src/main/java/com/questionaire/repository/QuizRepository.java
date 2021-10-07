package com.questionaire.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuizIdNotFoundException;

public interface QuizRepository {

	Long addQuiz(Long id, String subCode, Quiz quiz) throws DatabaseException;

	void checkQuizBySubCode(String subCode) throws QuizIdNotFoundException;

	void checkQuiz(Long id) throws QuizIdNotFoundException;

	List<QuizEntity> getQuiz(Long id, String subCode) throws DatabaseException;

	List<QuizEntity> getQuizBySubCode(String subCode) throws DatabaseException;

	List<List<QuizEntity>> getAllQuiz(List<Long> teacherList, List<String> subjectList) throws DatabaseException;

}
