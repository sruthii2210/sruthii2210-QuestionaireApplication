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
import com.questionaire.repository.ResultRepository;

@Repository
@Transactional
public class ResultRepositoryImpl implements ResultRepository{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public ResponseEntity<String> addResult(Long rollNo, String subCode, Long id, Integer score,Result result) {
		Session session=null;
		ResponseEntity<String> response=null;
		try {
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
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
			//session.getTransaction().commit();
			response=new ResponseEntity<String>("Result added Successfully!",new HttpHeaders(),HttpStatus.OK);
		}
		catch(HibernateException e)
		{
			return new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		
		return response;
	}
	@Override
	public List<Result> getResult(Long id) {
		List<Result> result=new ArrayList<Result>();
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Result where quiz.id=:id");
			query.setParameter("id", id);
			result=query.getResultList();
			
		}
		finally
		{
			
		}
		return result;
	}

}
