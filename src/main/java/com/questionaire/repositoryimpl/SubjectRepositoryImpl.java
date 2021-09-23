package com.questionaire.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questionaire.dto.Subject;
import com.questionaire.entity.ClassRoom;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.mapper.SubjectMapper;
import com.questionaire.repository.SubjectRepository;

@Repository
@Transactional
public class SubjectRepositoryImpl implements SubjectRepository {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ClassRepositoryImpl classRepo;

	public void checkSubject(String code) throws SubjectNotFoundException {
		Session session = sessionFactory.getCurrentSession();
		SubjectEntity subject = new SubjectEntity();
		Query query = session.createQuery("FROM Subject WHERE subCode=:subjectCode");
		query.setParameter("subjectCode", code);
		
		try {
			subject = (SubjectEntity) query.getSingleResult();
		} catch (NoResultException e) {

		}
		if (subject == null) {
			throw new SubjectNotFoundException("SubjectCode is not found!");
		}

	}

	void checkSubjectRoom(Long roomNo, String code) throws SubjectNotFoundException {
		SubjectEntity subject = new SubjectEntity();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Subject WHERE subCode=:subjectCode and classRoom.roomNo=:roomNo");
		query.setParameter("subjectCode", code);
		query.setParameter("roomNo", roomNo);
		subject = (SubjectEntity) query.list();
		if (subject == null) {
			throw new SubjectNotFoundException("SubjectCode or RoomNo is not Valid..Enter valid one!");
		}

	}

	public String addSubject(Long roomNo, Subject subject) throws DatabaseException {
		Session session = null;
		String response = null;
		try {
			session = sessionFactory.getCurrentSession();
			String subCode = (String) session.save(SubjectMapper.mapSubject(subject, roomNo));
			response = subCode;

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public List<SubjectEntity> getSubject(Long roomNo) throws DatabaseException {
		List<SubjectEntity> subject = new ArrayList<SubjectEntity>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Subject s where roomNo=:room");
			query.setParameter("room", roomNo);
			subject = query.getResultList();

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}
		return subject;
	}

	@Override
	public SubjectEntity updateSubject(Long roomNo, String subCode, Subject subject) throws DatabaseException {
		Session session = null;
		SubjectEntity response = null;
		try {
			session = sessionFactory.getCurrentSession();
			SubjectEntity sub = SubjectMapper.mapSubject(subject, roomNo);
			session.find(SubjectEntity.class, subCode);
			SubjectEntity subjectEntity = session.load(SubjectEntity.class, subCode);

			ClassRoom classDetails = new ClassRoom();
			classDetails.setRoomNo(roomNo);
			subjectEntity.setClassRoom(classDetails);

			subjectEntity.setSubName(subject.getSubName());
			session.merge(subjectEntity);

			response = subjectEntity;

		} catch (HibernateException e) {
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

			session.find(SubjectEntity.class, subCode);
			SubjectEntity subject = session.load(SubjectEntity.class, subCode);
			session.delete(subject);
			response = "Subject Details deleted Successfully!";

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

}
