package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionaire.dto.Result;
import com.questionaire.entity.ResultEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.QuizRepository;
import com.questionaire.repository.ResultRepository;
import com.questionaire.repository.StudentRepository;
import com.questionaire.repository.SubjectRepository;
import com.questionaire.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService{

	@Autowired
	private ResultRepository resultRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private QuizRepository quizRepository;
	
	@Override
	public Long addResult(Long rollNo, String subCode,Long id, Result result) throws ServiceException, NotFoundException {
		try{
			studentRepository.checkStudent(rollNo);
			subjectRepository.checkSubject(subCode);
			quizRepository.checkQuizBySubCode(subCode);
			quizRepository.checkQuiz(id);
			return resultRepository.addResult(rollNo,subCode,id,result);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<ResultEntity> getResult(Long id) throws ServiceException, QuizIdNotFoundException {
		try
		{
			quizRepository.checkQuiz(id);
		return resultRepository.getResult(id);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}

}
