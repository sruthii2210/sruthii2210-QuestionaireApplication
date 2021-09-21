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

import com.questionaire.entity.ClassRoom;
import com.questionaire.entity.Student;
import com.questionaire.entity.Subject;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.exception.StudentIdNotFoundException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.repository.StudentRepository;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ClassRepositoryImpl classRepo;
	
		public boolean checkStudent(Long rollNo) throws StudentIdNotFoundException {
			
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM Student WHERE rollNo=:rollNo");
			query.setParameter("rollNo", rollNo);
			Student stud = null;
			try {
		        stud =(Student) query.getSingleResult();
		        }
		        catch(NoResultException e)
		        {
		           
		        }
		   
		      if(stud==null)
		      {
		    	  throw new StudentIdNotFoundException("Student not Found,Enter the Valid Id!");
		      }
		      return true;
	}
		
		public boolean checkClassStud(Long roomNo,Long rollNo) throws StudentIdNotFoundException {
			
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM Student WHERE rollNo=:rollNo and roomNo=:roomNo");
			query.setParameter("rollNo", rollNo);
			query.setParameter("roomNo", roomNo);
			Student stud=null;
			try {
		        stud =(Student) query.getSingleResult();
		        }
		        catch(NoResultException e)
		        {
		           
		        }
		      if(stud==null)
		      {
		    	  throw new StudentIdNotFoundException("StudentId or RoomNo is Invalid..Enter valid one");
		      }
		      return true;
	}
	@Override
	public Student addStudent(Long roomNo,Student student) throws DatabaseException {
		Session session=null;
		Student response=null;
		try {
			session=sessionFactory.getCurrentSession();
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
			Long count = (Long) session.save(stud);
			if(count>0)
			{
				response = stud;
			}
			}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return response;
	}
	@Override
	public List<Student> getStudent(Long roomNo) throws DatabaseException {
		
		List<Student> student=new ArrayList<Student>();
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			
			boolean status=classRepo.checkClassRoomNo(roomNo);
			
			Query query=session.createQuery("from Student s where roomNo=:room");
			query.setParameter("room", roomNo);
			student=query.getResultList();
			
		}
		
		catch(HibernateException |  RoomNoNotFoundException  e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return student;

	}
	@Override
	public Student getStudentById(Long rollNo) throws DatabaseException {
		Student student;
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			boolean status=checkStudent(rollNo);
			Query query=session.createQuery("from Student s where s.rollNo=:rollNo");
			query.setParameter("rollNo", rollNo);
			student=(Student) query.getSingleResult();
			
		}
		catch(HibernateException | StudentIdNotFoundException  e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return student;
	}
	@Override
	public Student updateStudent(Long roomNo,Long rollNo, Student student) throws DatabaseException {
		
		Session session=null;
		Student response=null;
		try {
			session=sessionFactory.getCurrentSession();
		
			boolean status=checkClassStud(roomNo,rollNo);

			session.find(Student.class, rollNo);
			Student stud=session.load(Student.class, rollNo);
			
			stud.setAddress(student.getAddress());
			stud.setName(student.getName());
			stud.setDateOfBirth(student.getDateOfBirth());
			stud.setGender(student.getGender());
			ClassRoom classDetails=new ClassRoom();
			classDetails.setRoomNo(roomNo);
			stud.setClassRoom(classDetails);
			
			response=stud;
			}
		catch(HibernateException | StudentIdNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return response;
	}
	@Override
	public String deleteStudent(Long rollNo) throws DatabaseException {
		Session session=null;
		String response;
		try {
			session=sessionFactory.getCurrentSession();
			boolean status=checkStudent(rollNo);
			session.find(Student.class, rollNo);
			Student studentEntity = session.load(Student.class, rollNo);
			session.delete(studentEntity);
		
			response="Student Details deleted Successfully!";
		}
		catch(HibernateException | StudentIdNotFoundException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return response;
	}

}
