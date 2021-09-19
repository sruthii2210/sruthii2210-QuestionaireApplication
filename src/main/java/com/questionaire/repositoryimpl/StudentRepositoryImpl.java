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
import com.questionaire.entity.Student;
import com.questionaire.entity.Subject;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.repository.StudentRepository;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean checkSubject(String code) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Subject WHERE code=:subjectCode");
		query.setParameter("subjectCode", code);
		List<Subject> subjectList = query.list();
		if (subjectList.isEmpty()) {
			return false;
		}
		return true;
	}
		public boolean checkRoom(Long roomNo) {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM ClassRoom WHERE roomNo=:roomNo");
			query.setParameter("roomNo", roomNo);
			List<Class> cls = query.list();
			if (cls.isEmpty()) {
				return false;
			}
			return true;
	}
	@Override
	public ResponseEntity<String> addStudent(Long roomNo,Student student) {
		Session session=null;
		ResponseEntity<String> response=null;
		try {
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
			Student stud=new Student();
			ClassRoom classDetails=new ClassRoom();
			classDetails.setRoomNo(roomNo);
			stud.setClassRoom(classDetails);
			stud.setRollNo(student.getRollNo());
			stud.setName(student.getName());
			stud.setDateOfBirth(student.getDateOfBirth());
			stud.setGender(student.getGender());
			stud.setAddress(student.getAddress());
			
			session.save(stud);
			//session.getTransaction().commit();
			response=new ResponseEntity<String>("Student Details Added Successfully!",new HttpHeaders(),HttpStatus.OK);
		}
		catch(HibernateException e)
		{
			return new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		
		return response;
	}
	@Override
	public List<Student> getStudent(Long roomNo) throws DatabaseException {
		
		List<Student> student=new ArrayList<Student>();
		Session session=null;
			
		try {
			
			session=sessionFactory.getCurrentSession();
			if(!checkRoom(roomNo))
				throw new RoomNoNotFoundException("RoomNo not found");
			else {
			Query query=session.createQuery("from Student s where roomNo=:room");
			query.setParameter("room", roomNo);
			student=query.getResultList();
			}
		}
		
		catch(HibernateException| RoomNoNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return student;

	}
	@Override
	public List<Student> getStudentById(Long rollNo) {
		List<Student> student=new ArrayList<Student>();
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Student s where s.rollNo=:rollNo");
			query.setParameter("rollNo", rollNo);
			student=query.getResultList();
			
		}
		finally
		{
			
		}
		return student;
	}
	@Override
	public ResponseEntity<String> updateStudent(Long roomNo,Long rollNo, Student student) {
		
		Session session=null;
		ResponseEntity<String> response=null;
		try {
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
			session.find(Student.class, rollNo);
			Student stud=session.load(Student.class, rollNo);
			
			stud.setAddress(student.getAddress());
			stud.setName(student.getName());
			stud.setDateOfBirth(student.getDateOfBirth());
			stud.setGender(student.getGender());
			ClassRoom classDetails=new ClassRoom();
			classDetails.setRoomNo(roomNo);
			stud.setClassRoom(classDetails);
			
			//session.merge(stud);
			//session.flush();
			//session.getTransaction().commit();
			response=new ResponseEntity<String>("student Details updated Successfully!",new HttpHeaders(),HttpStatus.OK);
			}
		catch(HibernateException e)
		{
			return new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		
		return response;
	}
	@Override
	public ResponseEntity<String> deleteStudent(Long rollNo) {
		Session session=null;
		ResponseEntity<String> response=null;
		try {
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
			session.find(Student.class, rollNo);
			Student studentEntity = session.load(Student.class, rollNo);
			session.delete(studentEntity);
			//session.flush();
			//session.getTransaction().commit();
			response=new ResponseEntity<String>("Student Details deleted Successfully!",new HttpHeaders(),HttpStatus.OK);
		}
		catch(HibernateException e)
		{
			return new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		
		return response;
	}

}
