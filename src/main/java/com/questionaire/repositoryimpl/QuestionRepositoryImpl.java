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

import com.questionaire.dto.Question;
import com.questionaire.entity.QuestionEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.mapper.QuestionMapper;
import com.questionaire.repository.QuestionRepository;

@Repository
@Transactional
public class QuestionRepositoryImpl implements QuestionRepository {

	public static Logger logger = Logger.getLogger(QuestionRepositoryImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private QuizRepositoryImpl quiz;
	public void checkQuizByQuesNo(Long id,Integer quesNo) throws QuizIdNotFoundException
	{
		Session session=null;
		session=sessionFactory.getCurrentSession();
		QuestionEntity question;
		Query<QuestionEntity> query=session.createQuery("from QuestionEntity where quiz.id=:id and questionNo=:ques");
		query.setParameter("id",id);
		query.setParameter("ques", quesNo);
		logger.info("In checkQuizByQuesNo method...");
	        question =query.uniqueResultOptional().orElse(null);
	       
		if(question==null)
		{
			logger.warn("Warning in checkQuizByQuesNo method...");
			throw new QuizIdNotFoundException("Quiz Id or Question not found!");
		}
	}
	
	public void checkQuestion(Integer quesNo) throws QuestionNotFoundException
	{
		Session session=null;
		session=sessionFactory.getCurrentSession();
		QuestionEntity question;
		Query<QuestionEntity> query=session.createQuery("from QuestionEntity where questionNo=:ques");
		query.setParameter("ques", quesNo);
		logger.info("In checkQuestion method...");
	    question =query.uniqueResultOptional().orElse(null);
	   
	       
		if(question==null)
		{
			logger.warn("Warning in checkQuesNo method...");
			throw new QuestionNotFoundException("Question Not Found with "+quesNo+"!");
		}
		
	}
	
	@Override
	public Integer addQuestion(Long id, Question question) throws DatabaseException {
		Session session=null;
		Integer count=0;
		try {
			session=sessionFactory.getCurrentSession();
			count = (Integer) session.save(QuestionMapper.mapQuestion(id,question));
			if(count>0)
			{
				logger.info("Adding Question in Quiz "+id);
			}
			
		}
		catch(HibernateException e)
		{
			logger.error("Error in addQuestion...!");
			throw new DatabaseException(e.getMessage());
		}
		
		return count;
	}
	@Override
	public List<QuestionEntity> getQuestion(Long id) throws DatabaseException {
		
		Session session=null;
		List<QuestionEntity> question=new ArrayList<QuestionEntity>();
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from QuestionEntity where quiz.id=:id");
			query.setParameter("id", id);
			question=query.getResultList();
			logger.info("Fetching Questions...");
		
			}
		catch(HibernateException e)
		{
			logger.error("Error in fetching questions..");
			throw new DatabaseException(e.getMessage());
			
		}
		
		return question;
	}
	@Override
	public QuestionEntity updateQuestion(Long id, Integer quesNo, Question question) throws DatabaseException {
		Session session=null;
		QuestionEntity response=null;
		try {
			session=sessionFactory.getCurrentSession();
			QuestionEntity ques=QuestionMapper.mapQuestion(id, question);
			session.find(QuestionEntity.class, quesNo);
			QuestionEntity questionEntity=session.load(QuestionEntity.class, quesNo);
			questionEntity.setQuestion(question.getQuestion());
			response=(QuestionEntity) session.merge(questionEntity);
			logger.info("Updating question..");
		}
		catch(HibernateException e)
		{
			logger.info("Error in Updating question..");
			throw new DatabaseException(e.getMessage());
		}
		
		return response;
	}

	@Override
	public String deleteQuestion(Integer quesNo) throws DatabaseException {
		Session session=null;
		String response=null;
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("delete from QuestionEntity where quesNo=:ques");
			query.setParameter("ques", quesNo);
			query.executeUpdate();
			logger.info("Deleting question...");
			response="Question is removed Successfully!";
			
		}
		catch(HibernateException  e)
		{
			logger.error("Error in deleting question");
			throw new DatabaseException(e.getMessage());
		}
		
		return response;
	}

}
