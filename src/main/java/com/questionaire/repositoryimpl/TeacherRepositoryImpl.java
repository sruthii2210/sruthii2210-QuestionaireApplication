package com.questionaire.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

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

import com.questionaire.entity.Teacher;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuestionNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.repository.TeacherRepository;

@Repository
@Transactional
public class TeacherRepositoryImpl implements TeacherRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public boolean checkTeacher(Long id) throws TeacherNotFoundException {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		Object teacher = null;
		Query query = session.createQuery("from Teacher where id=:id");
		query.setParameter("id", id);

		try {
			teacher = query.getSingleResult();
		} catch (NoResultException e) {

		}
		if (teacher == null)
			throw new TeacherNotFoundException("Teacher Not Found with " + id + "!");
		return true;
	}

	@Override
	public Teacher addTeacherDetails(Teacher teacherDetails) throws DatabaseException {

		Session session = null;
		Teacher response = null;
		try {
			session = sessionFactory.getCurrentSession();

			Long count = (Long) session.save(teacherDetails);
			if (count > 0)
				response = teacherDetails;
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public List<Teacher> getAllTeacherDetails() throws DatabaseException {
		// TODO Auto-generated method stub
		Session session = null;
		List<Teacher> response = null;
		List<Teacher> teacherDetailsList = new ArrayList<>();
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM Teacher t");
			teacherDetailsList = query.list();
			response = teacherDetailsList;
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public Teacher updateTeacherDetails(Long id, Teacher teacherDetails) throws DatabaseException {
		Teacher response = null;
		Session session = null;
		try {
			boolean status = checkTeacher(id);
			session = sessionFactory.getCurrentSession();
			session.find(Teacher.class, id);
			Teacher updatedTeacher = session.load(Teacher.class, id);
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
		} catch (HibernateException | TeacherNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public ResponseEntity<String> deleteTeacherDetails(Long id) {
		
		Session session = null;
		ResponseEntity<String> response = null;
		try {
			session = sessionFactory.getCurrentSession();
			boolean checkTeacher = checkTeacher(id);
			if (!checkTeacher) {
				throw new TeacherNotFoundException("Teacher Not Found with" + " " + id + "!");
			}
			
			// session.beginTransaction();
			session.find(Teacher.class, id);
			Teacher teacherDetails = session.load(Teacher.class, id);
			session.delete(teacherDetails);
			// session.flush();
			// session.getTransaction().commit();
			response = new ResponseEntity<String>("Teacher Details Deleted Successfully!", new HttpHeaders(),
					HttpStatus.OK);
		} catch (HibernateException | TeacherNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), new HttpHeaders(), HttpStatus.OK);
		}

		return response;
	}

	@Override
	public Teacher getParticularTeacherDetails(Long id) throws DatabaseException {
		
		Teacher response = null;
		Session session = null;
		Teacher teacherDetails = new Teacher();
		try {
			session = sessionFactory.getCurrentSession();
			boolean status = checkTeacher(id);
			Query query = session.createQuery("FROM Teacher WHERE id=:teacherId");
			query.setParameter("teacherId", id);
			teacherDetails = (Teacher) query.getSingleResult();
			response = teacherDetails;
		} catch (HibernateException | TeacherNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

}
