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
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.repository.SubjectRepository;

@Repository
@Transactional
public class SubjectRepositoryImpl implements SubjectRepository {

	@Autowired
	private SessionFactory sessionFactory;
	public boolean checkSubject(String code) throws SubjectNotFoundException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Subject WHERE subCode=:subjectCode");
		query.setParameter("subjectCode", code);
		Object subject = query.list();
		if (subject==null) {
			throw new SubjectNotFoundException("SubjectCode is not found!");
		}
		return true;
	}
	
	public boolean checkRoomNo(Long roomNo) {
		List<ClassRoom> classRoom = new ArrayList<ClassRoom>();
		boolean status = false;
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from ClassRoom where roomNo=:rNo");
		query.setParameter("rNo",roomNo);
		classRoom=query.list();
		status=(!classRoom.isEmpty());
		return status;
		}
	public ResponseEntity<String> addSubject(Long roomNo, Subject subject) {
		Session session=null;
		ResponseEntity<String> response=null;
		try {
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
			Subject sub=new Subject();
			ClassRoom classDetails=new ClassRoom();
			classDetails.setRoomNo(roomNo);
			sub.setClassRoom(classDetails);
			sub.setSubCode(subject.getSubCode());
			sub.setSubName(subject.getSubName());
			session.save(sub);
			//session.getTransaction().commit();
			response=new ResponseEntity<String>("Subject Details Added Successfully!",new HttpHeaders(),HttpStatus.OK);
		}
		catch(HibernateException e)
		{
			return new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		
		return response;
	}
	@Override
	public List<Subject> getSubject(Long roomNo) {
		List<Subject> subject=new ArrayList<Subject>();
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Subject s where roomNo=:room");
			query.setParameter("room", roomNo);
			subject=query.getResultList();
			
		}
		finally
		{
			
		}
		return subject;
	}
	@Override
	public ResponseEntity<String> updateSubject(Long roomNo, String subCode, Subject subject) {
		Session session=null;
		ResponseEntity<String> response=null;
		try {
			if(checkRoomNo(roomNo))
			{
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
			session.find(Subject.class, subCode);
			Subject sub=session.load(Subject.class, subCode);
			
			
			ClassRoom classDetails=new ClassRoom();
			classDetails.setRoomNo(roomNo);
			sub.setClassRoom(classDetails);
			
			sub.setSubName(subject.getSubName());
			session.merge(sub);
			//session.flush();
			//session.getTransaction().commit();
			response=new ResponseEntity<String>("subject Details updated Successfully!",new HttpHeaders(),HttpStatus.OK);
			}
		}
		catch(HibernateException e)
		{
			return new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		
		return response;
	}
	@Override
	public ResponseEntity<String> deleteSubject(String subCode) {
		Session session=null;
		ResponseEntity<String> response=null;
		try {
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
			session.find(Subject.class, subCode);
			Subject subject = session.load(Subject.class, subCode);
			session.delete(subject);
			//session.flush();
			//session.getTransaction().commit();
			response=new ResponseEntity<String>("Subject Details deleted Successfully!",new HttpHeaders(),HttpStatus.OK);
		}
		catch(HibernateException e)
		{
			return new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		
		return response;
	}

}
