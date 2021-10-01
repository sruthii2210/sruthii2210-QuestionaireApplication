package com.questionaire.repositoryimpl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questionaire.dto.StudentLogin;
import com.questionaire.entity.StudentLoginEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.IdNotFoundException;
import com.questionaire.mapper.StudentLoginMapper;
import com.questionaire.repository.StudentLoginRepository;

@Repository
@Transactional
public class StudentLoginRepositoryImpl implements StudentLoginRepository {
	
	public static Logger logger = Logger.getLogger(StudentLoginRepositoryImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private StudentRepositoryImpl studentRepository;
	
	public void checkAutoId(Long id) throws IdNotFoundException {
		Session session = sessionFactory.getCurrentSession();
		Query<StudentLoginEntity> query = session.createQuery("FROM StudentLoginEntity WHERE autoId=:id");
		query.setParameter("id", id);
		StudentLoginEntity student = null;

		student = query.uniqueResultOptional().orElse(null);
		logger.info("In checkAutoId method in StudentLogin..!");
		if (student == null) {
			logger.error("Error in checkAutoId method in StudentLogin..!");
			throw new IdNotFoundException("No Record found for given Id " + id);
		}
	}

	@Override
	public Long createLogin(Long id, StudentLogin login) throws DatabaseException {
		Session session = null;
		Long autoId = 0l;
		StudentLoginEntity response = null;
		try {
			session = sessionFactory.getCurrentSession();

			autoId= (Long) session.save(StudentLoginMapper.mapStudentLogin(id, login));

			if (autoId > 0)
				logger.info("Login details created successfully...!");

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return autoId;

	}

	@Override
	public StudentLoginEntity getDetails(Long id) throws DatabaseException {
		StudentLoginEntity student;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

			Query<StudentLoginEntity> query = session.createQuery("from StudentLoginEntity where student.rollNo=:id");
			query.setParameter("id", id);
			student = query.uniqueResultOptional().orElse(null);
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}
		return student;
	}

	@Override
	public StudentLoginEntity updateLogin(Long id, StudentLogin login) throws DatabaseException {
		Session session = null;
		StudentLoginEntity response = null;
		try {
			session = sessionFactory.getCurrentSession();
			StudentLoginEntity studentLogin =StudentLoginMapper.mapStudentLogin(id, login);
			session.find(StudentLoginEntity.class, id);
			StudentLoginEntity updatedLogin = session.load(StudentLoginEntity.class, id);

			updatedLogin.setPassword(login.getPassword());

			session.merge(updatedLogin);

			response = updatedLogin;

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;

	}


}
