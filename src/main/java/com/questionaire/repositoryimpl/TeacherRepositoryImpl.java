package com.questionaire.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questionaire.dto.Teacher;
import com.questionaire.entity.TeacherEntity;
import com.questionaire.exception.DatabaseException;

import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.mapper.TeacherMapper;
import com.questionaire.repository.TeacherRepository;

@Repository
@Transactional
public class TeacherRepositoryImpl implements TeacherRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void checkTeacher(Long id) throws TeacherNotFoundException {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		TeacherEntity teacher = new TeacherEntity();
		Query<TeacherEntity> query = session.createQuery("from TeacherEntity where id=:id");
		query.setParameter("id", id);

		teacher = query.uniqueResultOptional().orElse(null);

		if (teacher == null)
			throw new TeacherNotFoundException("Teacher Not Found with " + id + "!");
	}

	@Override
	public Long addTeacherDetails(Teacher teacherDetails) throws DatabaseException {

		Session session = null;
		Long response = null;
		try {
			session = sessionFactory.getCurrentSession();

			Long teacherId = (Long) session.save(TeacherMapper.mapTeacher(teacherDetails));
			if (teacherId > 0)
				response = teacherId;
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public List<TeacherEntity> getAllTeacherDetails() throws DatabaseException {
		Session session = null;
		List<TeacherEntity> teacherDetailsList = new ArrayList<>();
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM TeacherEntity t");
			teacherDetailsList = query.list();

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return teacherDetailsList;
	}

	@Override
	public TeacherEntity updateTeacherDetails(Long id, Teacher teacherDetails) throws DatabaseException {
		TeacherEntity response = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			TeacherEntity teacher = TeacherMapper.mapTeacher(teacherDetails);

			session.find(TeacherEntity.class, id);
			TeacherEntity updatedTeacher = session.load(TeacherEntity.class, id);

			updatedTeacher.setFirstName(teacherDetails.getFirstName());
			updatedTeacher.setLastName(teacherDetails.getLastName());
			updatedTeacher.setDateOfBirth(teacherDetails.getDateOfBirth());
			updatedTeacher.setGender(teacherDetails.getGender());
			updatedTeacher.setQualification(teacherDetails.getQualification());
			updatedTeacher.setEmail(teacherDetails.getEmail());
			updatedTeacher.setContactNo(teacherDetails.getContactNo());
			updatedTeacher.setAddress(teacherDetails.getAddress());
			session.merge(updatedTeacher);
			response = updatedTeacher;

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public String deleteTeacherDetails(Long id) throws DatabaseException {

		Session session = null;
		String response = null;
		try {
			session = sessionFactory.getCurrentSession();

			session.find(TeacherEntity.class, id);
			TeacherEntity teacherDetails = session.load(TeacherEntity.class, id);
			session.delete(teacherDetails);

			response = "Teacher Details Deleted Successfully!";

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public TeacherEntity getParticularTeacherDetails(Long id) throws DatabaseException {

		TeacherEntity response = null;
		Session session = null;
		TeacherEntity teacherDetails = new TeacherEntity();
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM TeacherEntity WHERE id=:teacherId");
			query.setParameter("teacherId", id);
			teacherDetails = (TeacherEntity) query.getSingleResult();
			response = teacherDetails;
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

}
