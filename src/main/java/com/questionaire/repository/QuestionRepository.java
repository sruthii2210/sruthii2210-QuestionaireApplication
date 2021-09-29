package com.questionaire.repository;

import java.util.List;

import com.questionaire.dto.Question;
import com.questionaire.entity.QuestionEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;

public interface QuestionRepository {

	void checkQuizByQuesNo(Long id, Integer quesNo) throws QuizIdNotFoundException;

	void checkQuestion(Integer quesNo) throws QuestionNotFoundException;

	Integer addQuestion(Long id, Question question) throws DatabaseException;

	List<QuestionEntity> getQuestion(Long id) throws DatabaseException;

	QuestionEntity updateQuestion(Long id, Integer quesNo, Question question) throws DatabaseException;

	String deleteQuestion(Integer quesNo) throws DatabaseException;

	Long getQuestionCount(Long id) throws DatabaseException;

}
