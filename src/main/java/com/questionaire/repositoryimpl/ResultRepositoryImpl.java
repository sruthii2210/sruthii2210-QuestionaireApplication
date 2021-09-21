package com.questionaire.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questionaire.entity.ClassRoom;
import com.questionaire.entity.Quiz;
import com.questionaire.entity.Result;
import com.questionaire.entity.Student;
import com.questionaire.entity.Subject;
import com.questionaire.exception.DatabaseException;
import com.questionaire.repository.ResultRepository;

@Repository
@Transactional
public class ResultRepositoryImpl implements ResultRepository{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Result addResult(Long rollNo, String subCode, Long id, Integer score,Result result) throws DatabaseException {
		Session session=null;
		Result response=null;
		try {
			session=sessionFactory.getCurrentSession();
			Student stud=new Student();
			Subject sub=new Subject();
			Quiz quiz=new Quiz();
			
			stud.setRollNo(rollNo);
			sub.setSubCode(subCode);
			quiz.setAutoId(id);
			
			result.setQuiz(quiz);
			result.setStud(stud);
			result.setSub(sub);
			result.setScore(score);
			session.save(result);
		Long count=(Long) session.save(result);
			if(count>0)
			{
				response = result;
			}
			
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return response;
	}
	@Override
	public List<Result> getResult(Long id) throws DatabaseException {
		List<Result> result=new ArrayList<Result>();
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Result where quiz.id=:id");
			query.setParameter("id", id);
			result=query.getResultList();
			
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return result;
	}

}
