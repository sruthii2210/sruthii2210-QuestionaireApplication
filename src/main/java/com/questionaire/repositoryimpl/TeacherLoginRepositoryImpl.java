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
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.entity.Student;
import com.questionaire.entity.Teacher;
import com.questionaire.entity.TeacherLogin;
import com.questionaire.repository.TeacherLoginRepository;

@Repository
@Transactional
public class TeacherLoginRepositoryImpl implements TeacherLoginRepository{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public ResponseEntity<String> createLogin(Long id,TeacherLogin login) {
		Session session=null;
		ResponseEntity<String> response=null;
		try {
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
			Teacher teacher=new Teacher();
			TeacherLogin teachLogin=new TeacherLogin();
			
			teacher.setId(id);
			teachLogin.setUserid(teacher);
			teachLogin.setPassword(login.getPassword());
			
			session.save(teachLogin);
			//session.getTransaction().commit();
			response=new ResponseEntity<String>("Login Details created Successfully!",new HttpHeaders(),HttpStatus.OK);
		}
		catch(HibernateException e)
		{
			return new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		
		return response;

	}
	@Override
	public List<TeacherLogin> getDetails(Long id) {
		List<TeacherLogin> teacher=new ArrayList<TeacherLogin>();
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createSQLQuery("select * from login where id=:staffId");
			query.setParameter("staffId", id);
			teacher=query.getResultList();
			
		}
		finally
		{
			
		}
		return teacher;
	}
	@Override
	public ResponseEntity<String> updateLogin(Long id, TeacherLogin login) {
		Session session=null;
		ResponseEntity<String> response=null;
		try {
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
			session.find(TeacherLogin.class, id);
			TeacherLogin teachLogin=session.load(TeacherLogin.class, id);
			
			Teacher teacher=new Teacher();
			teacher.setId(id);
			teachLogin.setUserid(teacher);
			teachLogin.setPassword(login.getPassword());
			
			session.merge(teachLogin);
			//session.flush();
			//session.getTransaction().commit();
			response=new ResponseEntity<String>("Login Details updated Successfully!",new HttpHeaders(),HttpStatus.OK);
		}
		catch(HibernateException e)
		{
			return new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		
		return response;
			
	}
	

	
}
