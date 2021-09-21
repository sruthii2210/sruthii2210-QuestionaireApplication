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

import com.questionaire.entity.Student;
import com.questionaire.entity.Subject;
import com.questionaire.entity.Teacher;
import com.questionaire.entity.TeacherSubject;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.repository.TeacherSubjectRepository;
import com.questionaire.repositoryimpl.SubjectRepositoryImpl;

@Repository
@Transactional
public class TeacherSubjectRepositoryImpl implements TeacherSubjectRepository {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private TeacherRepositoryImpl teacherRepositoryImpl;
	@Autowired
	private SubjectRepositoryImpl subjectRepositoryImpl;

	@Override
	public TeacherSubject assignTeacherSubject(Long teacherId, String subjectCode, TeacherSubject teacherSubjectDetails)
			throws DatabaseException {
		TeacherSubject response = null;
		Session session = null;
		try {
			boolean checkTeacher = teacherRepositoryImpl.checkTeacher(teacherId);

			boolean checkSubject = subjectRepositoryImpl.checkSubject(subjectCode);

			session = sessionFactory.getCurrentSession();

			Teacher teacherDetails = new Teacher();
			teacherDetails.setId(teacherId);
			Subject subjectDetails = new Subject();
			subjectDetails.setSubCode(subjectCode);
			TeacherSubject teacherSubjectAssignDetails = new TeacherSubject();
			teacherSubjectAssignDetails.setTeacher(teacherDetails);
			teacherSubjectAssignDetails.setSubject(subjectDetails);

			Long count = (Long) session.save(teacherSubjectAssignDetails);
			if (count > 0)

				response = teacherSubjectAssignDetails;
		} catch (HibernateException | TeacherNotFoundException | SubjectNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public TeacherSubject updateTeacherSubjectAssign(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws DatabaseException {
		// TODO Auto-generated method stub
		TeacherSubject response = null;
		Session session = null;
		try {
			boolean checkTeacher = teacherRepositoryImpl.checkTeacher(teacherId);

			boolean checkSubject = subjectRepositoryImpl.checkSubject(subjectCode);

			session = sessionFactory.getCurrentSession();

			Query updateByTeacherId = session.createQuery("UPDATE TeacherSubject SET subCode=:code WHERE id=:staffId");
			updateByTeacherId.setParameter("code", subjectCode);
			updateByTeacherId.setParameter("staffId", teacherId);
			long countOfUpdationById = updateByTeacherId.executeUpdate();

			if (countOfUpdationById > 0) {
				response = (TeacherSubject) updateByTeacherId;
			}
		} catch (HibernateException | SubjectNotFoundException | TeacherNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return response;
	}

	@Override
	public String deleteTeacherSubjectAssign(Long teacherId, String subjectCode) throws DatabaseException {
		// TODO Auto-generated method stub
		String response = null;
		Session session = null;
		try {
			boolean checkTeacher = teacherRepositoryImpl.checkTeacher(teacherId);

			boolean checkSubject = subjectRepositoryImpl.checkSubject(subjectCode);

			session = sessionFactory.getCurrentSession();

			Query query = session.createQuery("DELETE FROM TeacherSubject WHERE id=:staffId AND subCode=:code");
			query.setParameter("staffId", teacherId);
			query.setParameter("code", subjectCode);
			long count = query.executeUpdate();

			if (count > 0) {
				response = "Subject Assigned for teacher deleted!";
			}
		} catch (HibernateException | SubjectNotFoundException | TeacherNotFoundException e) {
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
			boolean checkTeacher = teacherRepositoryImpl.checkTeacher(id);

			Query query = session.createQuery("SELECT new com.questionaire.entity.TeacherSubjectModel"
					+ "(t.teacher.id,t.subject.subCode) " + "FROM TeacherSubject t WHERE t.teacher.id=:id ");
			query.setParameter("id", id);
			teacher = query.getResultList();

		} catch (HibernateException | TeacherNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
		return teacher;
	}

}
