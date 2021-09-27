package com.questionaire.repositoryimpl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questionaire.dto.TeacherLogin;
import com.questionaire.entity.TeacherLoginEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.IdNotFoundException;
import com.questionaire.mapper.TeacherLoginMapper;
import com.questionaire.repository.TeacherLoginRepository;

@Repository
@Transactional
public class TeacherLoginRepositoryImpl implements TeacherLoginRepository {

	public static Logger logger = Logger.getLogger(TeacherLoginRepositoryImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private TeacherRepositoryImpl teacherRepo;

	public void checkAutoId(Long id) throws IdNotFoundException {
		Session session = sessionFactory.getCurrentSession();
		Query<TeacherLoginEntity> query = session.createQuery("FROM TeacherLoginEntity WHERE autoId=:id");
		query.setParameter("id", id);
		TeacherLoginEntity teacher = null;

		teacher = query.uniqueResultOptional().orElse(null);
		logger.info("In checkAutoId method in TeacherLogin..!");
		if (teacher == null) {
			logger.error("Error in checkAutoId method in TeacherLogin..!");
			throw new IdNotFoundException("No Record found for given Id " + id);
		}
	}

	@Override
	public Long createLogin(Long id, TeacherLogin login) throws DatabaseException {
		Session session = null;
		Long count = 0l;
		TeacherLoginEntity response = null;
		try {
			session = sessionFactory.getCurrentSession();

			count = (Long) session.save(TeacherLoginMapper.mapTeacherLogin(id, login));

			if (count > 0)
				logger.info("Login details created successfully...!");

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return count;

	}

	@Override
	public TeacherLoginEntity getDetails(Long id) throws DatabaseException {
		TeacherLoginEntity teacher;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

			Query<TeacherLoginEntity> query = session.createSQLQuery("select * from login where id=:staffId");
			query.setParameter("staffId", id);
			teacher = query.uniqueResultOptional().orElse(null);
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}
		return teacher;
	}

	@Override
	public TeacherLoginEntity updateLogin(Long id, TeacherLogin login) throws DatabaseException {
		Session session = null;
		TeacherLoginEntity response = null;
		try {
			session = sessionFactory.getCurrentSession();
			TeacherLoginEntity teacherLogin = TeacherLoginMapper.mapTeacherLogin(id, login);
			session.find(TeacherLoginEntity.class, id);
			TeacherLoginEntity teachLogin = session.load(TeacherLoginEntity.class, id);

			teachLogin.setPassword(login.getPassword());

			session.merge(teachLogin);

			response = teachLogin;

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;

	}

};