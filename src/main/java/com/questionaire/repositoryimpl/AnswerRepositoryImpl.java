package com.questionaire.repositoryimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questionaire.dto.Answer;
import com.questionaire.entity.AnswerEntity;
import com.questionaire.entity.ClassRoom;
import com.questionaire.entity.Question;
import com.questionaire.entity.Student;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.repository.AnswerRepository;
import com.sun.istack.logging.Logger;

@Repository
@Transactional
public class AnswerRepositoryImpl implements AnswerRepository {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private QuestionRepositoryImpl questionRepo;

	@Override
	public Long addAnswer(Integer quesNo, Answer answer) throws DatabaseException {
		Session session=null;
		AnswerEntity response=null;
		try {
			session=sessionFactory.getCurrentSession();
			Question q=new Question();
			q.setQuesNo(quesNo);
			answer.setQues(q);
			 session.save(answer);
			
			Long count = (Long) session.save(answer);
			if(count>0)
			{
				response = answer;
			}
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return response;
	}

	@Override
	public AnswerEntity getAnswer(Integer quesNo) throws DatabaseException, QuestionNotFoundException {
		Session session=null;
		AnswerEntity answer;
		
		
		try {
			Object obj=questionRepo.checkQuestion(quesNo);
			if(obj!=null) {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Answer where ques.quesNo=:quesNo");
			query.setParameter("quesNo", quesNo);
			answer=(AnswerEntity) query.getSingleResult();
			}
			else
			{
				throw new QuestionNotFoundException("Question is not found");
			}
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return answer;
	}

	@Override
	public AnswerEntity updateAnswer(Long autoId,Integer quesNo, AnswerEntity answer) throws DatabaseException {
		Session session=null;
		AnswerEntity response=null;
		
		try {
			
			session=sessionFactory.getCurrentSession();
			questionRepo.checkQuestion(quesNo);
			session.find(AnswerEntity.class, autoId);
			AnswerEntity ans=session.load(AnswerEntity.class, autoId);
			Question q=new Question();
			
			ans.setOption1(answer.getOption1());
			ans.setOption2(answer.getOption2());
			ans.setOption3(answer.getOption3());
			ans.setOption4(answer.getOption4());
			ans.setCrctAns(answer.getCrctAns());
			
			
			response=(AnswerEntity) session.merge(ans);
			}
		catch(HibernateException | QuestionNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}
	
		return response;
	}

}
