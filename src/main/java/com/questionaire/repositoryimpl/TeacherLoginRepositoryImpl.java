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
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.repository.TeacherLoginRepository;

@Repository
@Transactional
public class TeacherLoginRepositoryImpl implements TeacherLoginRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private TeacherRepositoryImpl teacherRepo;
	@Override
	public TeacherLogin createLogin(Long id,TeacherLogin login) throws DatabaseException {
		Session session=null;
		TeacherLogin response=null;
		try {
			session=sessionFactory.getCurrentSession();
			Teacher teacher=new Teacher();
			TeacherLogin teachLogin=new TeacherLogin();
			
			teacher.setId(id);
			teachLogin.setUserid(teacher);
			teachLogin.setPassword(login.getPassword());
			
			Long count=(Long) session.save(teachLogin);
			
			if(count>0)
			response=teachLogin;
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return response;

	}
	@Override
	public List<TeacherLogin> getDetails(Long id) throws DatabaseException {
		List<TeacherLogin> teacher=new ArrayList<TeacherLogin>();
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			boolean status=teacherRepo.checkTeacher(id);
			Query query=session.createSQLQuery("select * from login where id=:staffId");
			query.setParameter("staffId", id);
			teacher=query.getResultList();	
		}
		catch(HibernateException | TeacherNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return teacher;
	}
	@Override
	public TeacherLogin updateLogin(Long id, TeacherLogin login) throws DatabaseException {
		Session session=null;
		TeacherLogin response=null;
		try {
			session=sessionFactory.getCurrentSession();
			//boolean status=teacherRepo.checkTeacher(id);
			//System.out.println(status);
			session.find(TeacherLogin.class, id);
			TeacherLogin teachLogin=session.load(TeacherLogin.class, id);
			
			teachLogin.setPassword(login.getPassword());
			
			session.merge(teachLogin);
		
			response=teachLogin;
			
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return response;
			
	}
	

	
}
;