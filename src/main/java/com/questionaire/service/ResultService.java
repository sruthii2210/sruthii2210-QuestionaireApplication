package com.questionaire.service;

import java.util.List;

import com.questionaire.dto.Result;
import com.questionaire.entity.ResultEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.exception.ServiceException;

public interface ResultService {

	Long addResult(Long rollNo, String subCode, Long id, Result result) throws ServiceException, NotFoundException;

	List<ResultEntity> getResult(Long id) throws ServiceException, QuizIdNotFoundException;

	List<ResultEntity> getResultByRollNo(Long rollNo, Long id) throws ServiceException, NotFoundException;

}
