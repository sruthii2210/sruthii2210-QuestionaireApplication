package com.questionaire.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.repository.QuizRepository;
import com.questionaire.repository.SubjectRepository;
import com.questionaire.repository.TeacherRepository;
import com.questionaire.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public Long addQuiz(Long id, String subCode, Quiz quiz) throws ServiceException, NotFoundException {
		try {
			teacherRepository.checkTeacher(id);
			subjectRepository.checkSubject(subCode);

			return quizRepository.addQuiz(id, subCode, quiz);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<QuizEntity> getQuiz(Long id, String subCode) throws ServiceException, NotFoundException {

		try {
			teacherRepository.checkTeacher(id);
			subjectRepository.checkSubject(subCode);
			List<QuizEntity> quiz = quizRepository.getQuiz(id, subCode);
			return quiz;
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<QuizEntity> getQuizBySubCode(String subCode) throws ServiceException, NotFoundException {
		List<QuizEntity> quiz;
		try {
			subjectRepository.checkSubject(subCode);
			quiz = quizRepository.getQuizBySubCode(subCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		return quiz;
	}

	@Override
	public List<List<QuizEntity>> getAllQuiz(List<Long> teacherList, List<String> subjectList) throws ServiceException {
		List<List<QuizEntity>> quiz;
		try {
			
			quiz = quizRepository.getAllQuiz(teacherList,subjectList);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		return quiz;
	}

	@Override
	public QuizEntity updateQuiz(Long id,Long quizId, String subCode, Quiz quiz) throws NotFoundException, ServiceException {
		try {
			quizRepository.checkQuiz(quizId);
			
			return quizRepository.updateClass(id,quizId,subCode,quiz);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public QuizEntity getQuizById(Long id) throws NotFoundException, ServiceException {
		QuizEntity quiz;
		try {
			quizRepository.checkQuiz(id);
			quiz = quizRepository.getQuizById(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		return quiz;
	}

	@Override
	public List<QuizEntity> getQuizByStaff(Long id, String subCode) throws ServiceException, NotFoundException {
		try {
			teacherRepository.checkTeacher(id);
			subjectRepository.checkSubject(subCode);
			List<QuizEntity> quiz = quizRepository.getQuizByStaff(id, subCode);
			return quiz;
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	

}
