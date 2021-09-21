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
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.repository.SubjectRepository;

@Repository
@Transactional
public class SubjectRepositoryImpl implements SubjectRepository {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ClassRepositoryImpl classRepo;

	public boolean checkSubject(String code) throws SubjectNotFoundException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Subject WHERE subCode=:subjectCode");
		query.setParameter("subjectCode", code);
		Object subject = query.list();
		if (subject == null) {
			throw new SubjectNotFoundException("SubjectCode is not found!");
		}
		return true;
	}

	boolean checkSubjectRoom(Long roomNo,String code) throws SubjectNotFoundException
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Subject WHERE subCode=:subjectCode and classRoom.roomNo=:roomNo");
		query.setParameter("subjectCode", code);
		query.setParameter("roomNo", roomNo);
		Object subject = query.list();
		if (subject == null) {
			throw new SubjectNotFoundException("SubjectCode or RoomNo is not Valid..Enter valid one!");
		}
		return true;
	}
	public Subject addSubject(Long roomNo, Subject subject) throws DatabaseException {
		Session session = null;
		Subject response = null;
		try {
			session = sessionFactory.getCurrentSession();
			Subject sub = new Subject();
			ClassRoom classDetails = new ClassRoom();
			classDetails.setRoomNo(roomNo);
			sub.setClassRoom(classDetails);
			sub.setSubCode(subject.getSubCode());
			sub.setSubName(subject.getSubName());
					session.save(sub);
				response = sub;

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public List<Subject> getSubject(Long roomNo) throws DatabaseException {
		List<Subject> subject = new ArrayList<Subject>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			boolean status=classRepo.checkClassRoomNo(roomNo);
			Query query = session.createQuery("from Subject s where roomNo=:room");
			query.setParameter("room", roomNo);
			subject = query.getResultList();

		} 
		catch (HibernateException | RoomNoNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return subject;
	}

	@Override
	public Subject updateSubject(Long roomNo, String subCode, Subject subject) throws DatabaseException {
		Session session = null;
		Subject response = null;
		try {
				session = sessionFactory.getCurrentSession();
				
				boolean status=checkSubjectRoom(roomNo,subCode);
				session.find(Subject.class, subCode);
				Subject sub = session.load(Subject.class, subCode);

				ClassRoom classDetails = new ClassRoom();
				classDetails.setRoomNo(roomNo);
				sub.setClassRoom(classDetails);

				sub.setSubName(subject.getSubName());
				session.merge(sub);
				
				response = sub;
				
		} catch (HibernateException | SubjectNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public String deleteSubject(String subCode) throws DatabaseException {
		Session session = null;
		String response = null;
		try {
			session = sessionFactory.getCurrentSession();
			boolean status=checkSubject(subCode);
			session.find(Subject.class, subCode);
			Subject subject = session.load(Subject.class, subCode);
			session.delete(subject);
			response = "Subject Details deleted Successfully!";
				
		} catch (HibernateException | SubjectNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

}
