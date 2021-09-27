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

import com.questionaire.dto.Subject;
import com.questionaire.entity.ClassRoom;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.mapper.SubjectMapper;
import com.questionaire.repository.SubjectRepository;
import org.apache.log4j.Logger;

@Repository
@Transactional
public class SubjectRepositoryImpl implements SubjectRepository {

	public static Logger logger = Logger.getLogger(SubjectRepositoryImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ClassRepositoryImpl classRepo;

	public void checkSubject(String code) throws SubjectNotFoundException {
		Session session = sessionFactory.getCurrentSession();
		SubjectEntity subject = new SubjectEntity();
		Query<SubjectEntity> query = session.createQuery("FROM SubjectEntity WHERE code=:subjectCode");
		query.setParameter("subjectCode", code);

		subject = query.uniqueResultOptional().orElse(null);

		if (subject == null) {
			logger.warn("In checkSubject method...");
			throw new SubjectNotFoundException("SubjectCode is not found!");
		}

	}

//	public void checkSubjectRoom(String standard, String code) throws SubjectNotFoundException {
//		SubjectEntity subject = new SubjectEntity();
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<SubjectEntity> query = session.createQuery("FROM SubjectEntity WHERE subCode=:subjectCode");
//		query.setParameter("subjectCode", code);
//		query.setParameter("standard", standard);
//		subject = query.uniqueResultOptional().orElse(null);
//		logger.info("In checkSubject Method...");
//		if (subject == null) {
//			logger.warn("In checkSubjectRoom method...");
//			throw new SubjectNotFoundException("SubjectCode or RoomNo is not Valid..Enter valid one!");
//		}
//
//	}

	public String addSubject(String standard, Subject subject) throws DatabaseException {
		Session session = null;
		String subCode = null;
		try {
			session = sessionFactory.getCurrentSession();
			subCode = (String) session.save(SubjectMapper.mapSubject(subject, standard));
			logger.info("In addSubject Method...");

		} catch (HibernateException e) {
			logger.warn("In HibernateException(addSubject)...!");
			throw new DatabaseException(e.getMessage());
		}

		return subCode;
	}

	@Override
	public List<SubjectEntity> getSubject(String standard) throws DatabaseException {
		List<SubjectEntity> subject = new ArrayList<>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from SubjectEntity s where standard=:standard");
			query.setParameter("standard", standard);
			subject = query.getResultList();
			logger.info("In getSubject Method for particular class...");

		} catch (HibernateException e) {
			logger.warn("In HibernateException(getSubject)...!");
			throw new DatabaseException(e.getMessage());
		}
		return subject;
	}

	@Override
	public SubjectEntity updateSubject(String standard, String code, Subject subject) throws DatabaseException {
		Session session = null;
		SubjectEntity response = null;
		try {
			session = sessionFactory.getCurrentSession();
			SubjectEntity sub = SubjectMapper.mapSubject(subject, standard);
			session.find(SubjectEntity.class, code);
			SubjectEntity subjectEntity = session.load(SubjectEntity.class, code);
			logger.info("In updateSubject Method ...");

			// subjectEntity.setClassRoom(classDetails);

			subjectEntity.setName(subject.getName());
			// subjectEntity.setStandard(subject.getStandard());
			session.merge(subjectEntity);

			response = subjectEntity;

		} catch (HibernateException e) {
			logger.warn("In HibernateException(updateSubject)...!");
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public String deleteSubject(String code) throws DatabaseException {
		Session session = null;
		String response = null;
		try {
			session = sessionFactory.getCurrentSession();

			session.find(SubjectEntity.class, code);
			SubjectEntity subject = session.load(SubjectEntity.class, code);
			logger.info("In deleteSubject Method ...");
			session.delete(subject);
			response = "Subject Details deleted Successfully!";

		} catch (HibernateException e) {
			logger.warn("In HibernateException(deleteSubject)...!");
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

}
