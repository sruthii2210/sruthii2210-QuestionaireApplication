package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionaire.dto.Question;
import com.questionaire.entity.QuestionEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.QuestionRepository;
import com.questionaire.repository.QuizRepository;
import com.questionaire.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuizRepository quizRepository;

	@Override
	public Integer addQuestion(Long id, Question question) throws ServiceException, NotFoundException {
		try {
			quizRepository.checkQuiz(id);
			Integer ques = questionRepository.addQuestion(id, question);
			return ques;
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<QuestionEntity> getQuestion(Long id) throws ServiceException, NotFoundException {
		try {
			quizRepository.checkQuiz(id);
			List<QuestionEntity> q = questionRepository.getQuestion(id);
			return q;
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public QuestionEntity updateQuestion(Long id, Integer quesNo, Question question)
			throws ServiceException, NotFoundException {
		try {
			quizRepository.checkQuiz(id);
			questionRepository.checkQuestion(quesNo);
			questionRepository.checkQuizByQuesNo(id, quesNo);
			return questionRepository.updateQuestion(id, quesNo, question);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String deleteQuestion(Integer quesNo) throws ServiceException, NotFoundException {
		try {
			questionRepository.checkQuestion(quesNo);
			questionRepository.deleteQuestion(quesNo);
			String response = "Question is removed Successfully!";
			return response;
		}

		catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
