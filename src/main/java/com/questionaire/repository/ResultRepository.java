package com.questionaire.repository;

import java.util.List;

import com.questionaire.dto.Result;
import com.questionaire.entity.ResultEntity;
import com.questionaire.exception.DatabaseException;

public interface ResultRepository {

	Long addResult(Long rollNo, String subCode, Long id, Result result) throws DatabaseException;

	List<ResultEntity> getResult(Long id) throws DatabaseException;

}
