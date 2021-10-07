package com.questionaire.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questionaire.dto.Result;
import com.questionaire.entity.ResultEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.mapper.ResultMapper;
import com.questionaire.repository.ResultRepository;

@Repository
@Transactional
public class ResultRepositoryImpl implements ResultRepository {

	public static Logger logger = Logger.getLogger(ResultRepositoryImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long addResult(Long rollNo, String subCode, Long id, Result result) throws DatabaseException {
		Session session = null;
		Long autoId = 0l;
		try {
			session = sessionFactory.getCurrentSession();

			autoId = (Long) session.save(ResultMapper.mapResult(rollNo, subCode, id, result));
			logger.info("Adding result to student...");

		} catch (HibernateException e) {
			logger.error("Error in adding result to student...");
			throw new DatabaseException(e.getMessage());
		}
		return autoId;
	}

	@Override
	public List<ResultEntity> getResult(Long id) throws DatabaseException {
		List<ResultEntity> result = new ArrayList<>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

			Query query = session.createQuery("FROM ResultEntity r WHERE r.quiz.id=:id ");
			query.setParameter("id", id);
			result = query.getResultList();
			logger.info("Fetching results for quiz " + id);

		} catch (HibernateException e) {
			logger.error("Error in fetching results for quiz " + id);
			throw new DatabaseException(e.getMessage());
		}
		return result;
	}

	@Override
	public List<ResultEntity> getResultByRollNo(Long rollNo, Long id) throws DatabaseException {
		List<ResultEntity> result = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

			Query query = session.createQuery(" FROM ResultEntity r WHERE r.quiz.id=:id and r.student.rollNo=:rollNo");
			query.setParameter("id", id);
			query.setParameter("rollNo", rollNo);
			result = query.getResultList();
			logger.info("Fetching results for quiz " + id);

		} catch (HibernateException e) {
			logger.error("Error in fetching results for quiz " + id);
			throw new DatabaseException(e.getMessage());
		}
		return result;
	}

	public List<ResultEntity> getResultByCode(Long rollNo, String code) throws DatabaseException {
		List<ResultEntity> result = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

			Query query = session
					.createQuery(" FROM ResultEntity r WHERE r.subject.code=:code and r.student.rollNo=:rollNo");
			query.setParameter("code", code);
			query.setParameter("rollNo", rollNo);
			result = query.getResultList();
			logger.info("Fetching results for quiz " + code);

		} catch (HibernateException e) {
			logger.error("Error in fetching results for quiz " + code);
			throw new DatabaseException(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Integer> getResultByQuizId(Long rollNo, List<Long> quizIds) throws DatabaseException {
		List<Integer> result = new ArrayList<>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

			for (int i = 0; i < quizIds.size(); i++) {
				Query query = session.createQuery("select score FROM ResultEntity r WHERE r.quiz.autoId=:autoId and r.student.rollNo=:rollNo");
				query.setParameter("rollNo", rollNo);
				query.setParameter("autoId", quizIds.get(i));
				
				result.add((Integer) query.getSingleResult());
			}
			logger.info("Fetching results for quiz ");

		} catch (HibernateException e) {
			logger.error("Error in fetching results for quiz ");
			throw new DatabaseException(e.getMessage());
		}
		return result;
	}

}
