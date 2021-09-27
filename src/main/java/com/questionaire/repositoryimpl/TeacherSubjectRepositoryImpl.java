package com.questionaire.repositoryimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questionaire.dto.TeacherSubject;
import com.questionaire.entity.TeacherSubjectEntity;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.DatabaseException;
import com.questionaire.mapper.TeacherSubjectMapper;
import com.questionaire.repository.TeacherSubjectRepository;

@Repository
@Transactional
public class TeacherSubjectRepositoryImpl implements TeacherSubjectRepository {

	public static Logger logger = Logger.getLogger(TeacherSubjectRepositoryImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long assignTeacherSubject(Long teacherId, String subjectCode, Long roomNo,
			TeacherSubject teacherSubjectDetails) throws DatabaseException {
		Long count = 0l;
		Session session = null;
		try {

			session = sessionFactory.getCurrentSession();
			count = (Long) session.save(
					TeacherSubjectMapper.mapTeacherSubject(teacherId, subjectCode, roomNo, teacherSubjectDetails));
			if (count > 0)
				logger.info("Subjects are assigned to staffs successfully...!");

		} catch (HibernateException e) {
			logger.error("Error in assigning subjects to staffs...!");
			throw new DatabaseException(e.getMessage());
		}

		return count;
	}

	@Override
	public TeacherSubjectEntity updateTeacherSubjectAssign(Long teacherId, String subjectCode, Long roomNo,
			TeacherSubject teacherSubjectDetails) throws DatabaseException {
		TeacherSubjectEntity response = null;
		Session session = null;
		try {

			session = sessionFactory.getCurrentSession();

			TeacherSubjectEntity teacherSubject = TeacherSubjectMapper.mapTeacherSubject(teacherId, subjectCode, roomNo,
					teacherSubjectDetails);
			Query updateByTeacherId = session
					.createQuery("UPDATE TeacherSubjectEntity SET code=:code WHERE id=:staffId");
			updateByTeacherId.setParameter("code", subjectCode);
			updateByTeacherId.setParameter("staffId", teacherId);
			long countOfUpdationById = updateByTeacherId.executeUpdate();
			session.merge(updateByTeacherId);

			response = (TeacherSubjectEntity) updateByTeacherId;

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}
		return response;
	}

	@Override
	public String deleteTeacherSubjectAssign(Long teacherId, String subjectCode) throws DatabaseException {

		String response = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

			Query query = session.createQuery("DELETE FROM TeacherSubjectEntity WHERE id=:staffId AND code=:code");
			query.setParameter("staffId", teacherId);
			query.setParameter("code", subjectCode);
			long count = query.executeUpdate();

			if (count > 0) {
				response = "Subject Assigned for teacher deleted!";
			}
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}
		return response;
	}

	@Override
	public List<TeacherSubjectModel> getSubject(Long id) throws DatabaseException {
		List<TeacherSubjectModel> teacher;
		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();

			Query query = session.createQuery("SELECT new com.questionaire.entity.TeacherSubjectModel"
					+ "(t.teacher.id,t.subject.code) " + "FROM TeacherSubjectEntity t WHERE t.teacher.id=:id ");
			query.setParameter("id", id);
			teacher = query.getResultList();

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}
		return teacher;
	}

	@Override
	public TeacherSubjectModel getQuiz(Long roomNo, String code) throws DatabaseException {
		TeacherSubjectModel teacher;
		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();

			Query<TeacherSubjectModel> query = session
					.createQuery("SELECT new com.questionaire.entity.TeacherSubjectModel"
							+ "(t.classRoom.roomNo,t.teacher.id,t.subject.code) "
							+ "FROM TeacherSubjectEntity t WHERE t.classRoom.roomNo=:roomNo and t.subject.code=:code ");
			query.setParameter("roomNo", roomNo);
			query.setParameter("code", code);
			teacher = query.uniqueResultOptional().orElse(null);

		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}
		return teacher;

	}

}
