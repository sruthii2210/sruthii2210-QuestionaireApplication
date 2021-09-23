package com.questionaire.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
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

import com.questionaire.entity.ClassRoom;
import com.questionaire.entity.Question;
import com.questionaire.entity.QuizEntity;
import com.questionaire.entity.Student;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.repository.QuestionRepository;

@Repository
@Transactional
public class QuestionRepositoryImpl implements QuestionRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private QuizRepositoryImpl quiz;
	public boolean checkQuiz(Long id,Integer quesNo) throws QuizIdNotFoundException
	{
		Session session=null;
		session=sessionFactory.getCurrentSession();
		Object quiz=null;
		Query query=session.createQuery("from Question where quiz.id=:id and quesNo=:ques");
		query.setParameter("id",id);
		query.setParameter("ques", quesNo);
		//List quiz=query.getResultList();
		try {
	        quiz =query.getSingleResult();
	        }
	        catch(NoResultException e)
	        {
	           return false;
	        }
		if(quiz==null)
			throw new QuizIdNotFoundException("Quiz Id or Question not found!");
		return true;
	}
	
	public boolean checkQuestion(Integer quesNo) throws QuestionNotFoundException
	{
		Session session=null;
		session=sessionFactory.getCurrentSession();
		Object question=null;
		Query query=session.createQuery("from Question where quesNo=:ques");
		query.setParameter("ques", quesNo);
		//List ques=query.getResultList();
		try {
	        question =query.getSingleResult();
	        }
	        catch(NoResultException e)
	        {
	           
	        }
		if(question==null)
			throw new QuestionNotFoundException("Question Not Found with "+quesNo+"!");
		return true;
	}
	
	@Override
	public Question addQuestion(Long id, Question question) throws DatabaseException {
		Session session=null;
		Question response=null;
		try {
			session=sessionFactory.getCurrentSession();
			QuizEntity q=new QuizEntity();
			q.setAutoId(id);
			question.setQuiz(q);
			question.setQuestion(question.getQuestion());
			session.save(question);
			Integer count = (Integer) session.save(question);
			if(count>0)
			{
				response = question;
			}
			
			
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return response;
	}
	@Override
	public List<Question> getQuestion(Long id) throws DatabaseException {
		
		Session session=null;
		List<Question> question=new ArrayList<Question>();
		try {
			session=sessionFactory.getCurrentSession();
			
			quiz.checkQuiz(id);
			Query query=session.createQuery("from Question where quiz.id=:id");
			query.setParameter("id", id);
			question=query.getResultList();
		
			}
		catch(HibernateException | QuizIdNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
			
		}
		
		return question;
	}
	@Override
	public Question updateQuestion(Long id, Integer quesNo, Question question) throws DatabaseException {
		Session session=null;
		Question response=null;
		try {
			session=sessionFactory.getCurrentSession();
			boolean status=checkQuiz(id,quesNo);
			session.find(Question.class, quesNo);
			Question ques=session.load(Question.class, quesNo);
			ques.setQuestion(question.getQuestion());
			response=(Question) session.merge(ques);
		}
		catch(HibernateException | QuizIdNotFoundException e)
		{
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
			boolean status=checkQuestion(quesNo);
			Query query=session.createQuery("delete from Question where quesNo=:ques");
			query.setParameter("ques", quesNo);
			query.executeUpdate();
			response="Question is removed Successfully!";
			
		}
		catch(HibernateException | QuestionNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return response;
	}

}
