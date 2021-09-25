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

import com.questionaire.dto.Student;
import com.questionaire.entity.ClassRoom;
import com.questionaire.entity.StudentEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.StudentIdNotFoundException;
import com.questionaire.mapper.StudentMapper;
import com.questionaire.repository.StudentRepository;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

	public static Logger logger = Logger.getLogger(StudentRepositoryImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ClassRepositoryImpl classRepo;

	public void checkStudent(Long rollNo) throws StudentIdNotFoundException {

		Session session = sessionFactory.getCurrentSession();
		Query<StudentEntity> query = session.createQuery("FROM StudentEntity WHERE rollNo=:rollNo");
		query.setParameter("rollNo", rollNo);
		StudentEntity stud = null;

		stud = query.uniqueResultOptional().orElse(null);
		logger.info("In checkStudent method..!");
		if (stud == null) {
			logger.error("Error in checkStudent method..!");
			throw new StudentIdNotFoundException("Student not Found,Enter the Valid Id!");
		}

	}

	public void checkClassStud(Long roomNo, Long rollNo) throws StudentIdNotFoundException {

		Session session = sessionFactory.getCurrentSession();
		Query<StudentEntity> query = session.createQuery("FROM StudentEntity WHERE rollNo=:rollNo and roomNo=:roomNo");
		query.setParameter("rollNo", rollNo);
		query.setParameter("roomNo", roomNo);
		StudentEntity stud = null;

		stud = query.uniqueResultOptional().orElse(null);
		logger.info("In checkClassStudent method..!");

		if (stud == null) {
			logger.error("Error in checkClassStudent method..!");
			throw new StudentIdNotFoundException("StudentId or RoomNo is Invalid..Enter valid one");
		}

	}

	@Override
	public Long addStudent(Long roomNo, Student student) throws DatabaseException {
		Session session = null;
		Long studId = 0l;
		try {
			session = sessionFactory.getCurrentSession();
			studId = (Long) session.save(StudentMapper.mapStudent(roomNo, student));
			if (studId > 0)

				logger.info("Student is added successfully..!");

		} catch (HibernateException e) {
			logger.error("HibernateException...!");
			throw new DatabaseException(e.getMessage());
		}
		return studId;
	}

	@Override
	public List<StudentEntity> getStudent(Long roomNo) throws DatabaseException {

		List<StudentEntity> student = new ArrayList<>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

			Query query = session.createQuery("from StudentEntity s where roomNo=:room");
			query.setParameter("room", roomNo);
			student = query.getResultList();
			logger.info("Fetching studentDetails for roomNo "+roomNo);
		}

		catch (HibernateException e) {
			logger.error("Error in fetching studentDetails for roomNo "+roomNo);
			throw new DatabaseException(e.getMessage());
		}

		return student;

	}

	@Override
	public StudentEntity getStudentById(Long rollNo) throws DatabaseException {
		StudentEntity student;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery("from StudentEntity s where s.rollNo=:rollNo");
			query.setParameter("rollNo", rollNo);
			student = (StudentEntity) query.getSingleResult();
			logger.info("Fetching studentDetails of rollNo "+rollNo+" ..!");

		} catch (HibernateException  e) {
			logger.error("Error in fetching studentDetails of rollNo "+rollNo+" ..!");
			throw new DatabaseException(e.getMessage());
		}
		return student;
	}

	@Override
	public StudentEntity updateStudent(Long roomNo, Long rollNo, Student student) throws DatabaseException {

		Session session = null;
		StudentEntity response = null;
		try {
			session = sessionFactory.getCurrentSession();
			StudentEntity studentEntity=StudentMapper.mapStudent(roomNo, student);
			session.find(StudentEntity.class, rollNo);
			StudentEntity stud = session.load(StudentEntity.class, rollNo);
			
			stud.setAddress(student.getAddress());
			stud.setName(student.getName());
			stud.setDateOfBirth(student.getDateOfBirth());
			stud.setGender(student.getGender());
			ClassRoom classDetails = new ClassRoom();
			classDetails.setRoomNo(roomNo);
			stud.setClassRoom(classDetails);
			logger.info("updating studentDetails of rollNo "+rollNo);
			response = stud;
		} catch (HibernateException  e) {
			logger.error("Error in updating studentDetails of rollNo "+rollNo);
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public String deleteStudent(Long rollNo) throws DatabaseException {
		Session session = null;
		String response;
		try {
			session = sessionFactory.getCurrentSession();
			session.find(StudentEntity.class, rollNo);
			StudentEntity studentEntity = session.load(StudentEntity.class, rollNo);
			session.delete(studentEntity);
			logger.info("Deleting studentDetails of rollNo "+rollNo);
			response = "Student Details deleted Successfully!";
		} catch (HibernateException e) {
			logger.info("Error in deleting studentDetails of rollNo "+rollNo);
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

}
