package com.questionaire.repositoryimpl;

import javax.persistence.Query;
import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questionaire.dto.Answer;
import com.questionaire.entity.AnswerEntity;

import com.questionaire.entity.QuestionEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;

import com.questionaire.mapper.AnswerMapper;
import com.questionaire.repository.AnswerRepository;


@Repository
@Transactional
public class AnswerRepositoryImpl implements AnswerRepository {

	public static Logger logger=Logger.getLogger(AnswerRepositoryImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private QuestionRepositoryImpl questionRepo;

	@Override
	public Long addAnswer(Integer quesNo, Answer answer) throws DatabaseException {
		Session session=null;
		Long count=0l;
		try {
			session=sessionFactory.getCurrentSession();
			count = (Long) session.save(AnswerMapper.mapAnswer(quesNo, answer));
			if(count>0)
			{
				logger.info("Answer is added to question "+quesNo);
		
			}
		}
		catch(HibernateException e)
		{
			logger.error("Error in adding answers to question "+quesNo);
			throw new DatabaseException(e.getMessage());
		}
		
		return count;
	}

	@Override
	public AnswerEntity getAnswer(Integer quesNo) throws DatabaseException, QuestionNotFoundException {
		Session session=null;
		AnswerEntity answer;
		
		
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from AnswerEntity where ques.quesNo=:quesNo");
			query.setParameter("quesNo", quesNo);
			logger.info("Fetching answer for question "+quesNo);
			answer=(AnswerEntity) query.getSingleResult();
		}
		catch(HibernateException e)
		{
			logger.error("Error in fetching answer to question "+quesNo);
			throw new DatabaseException(e.getMessage());
		}
		return answer;
	}

	@Override
	public AnswerEntity updateAnswer(Long autoId,Integer quesNo, Answer answer) throws DatabaseException {
		Session session=null;
		AnswerEntity response=null;
		
		try {
			
			session=sessionFactory.getCurrentSession();
			AnswerEntity answerEntity=AnswerMapper.mapAnswer(quesNo, answer);
			session.find(AnswerEntity.class, autoId);
			AnswerEntity ans=session.load(AnswerEntity.class, autoId);
			
			ans.setOption1(answer.getOption1());
			ans.setOption2(answer.getOption2());
			ans.setOption3(answer.getOption3());
			ans.setOption4(answer.getOption4());
			ans.setCrctAns(answer.getCrctAns());
			
			response=(AnswerEntity) session.merge(ans);
			logger.info("Answer is updated to question "+quesNo);

			}
		catch(HibernateException  e)
		{
			logger.info("Error in updating answer for question "+quesNo);
			throw new DatabaseException(e.getMessage());
		}
	
		return response;
	}

}
