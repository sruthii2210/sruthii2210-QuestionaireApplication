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
public class TeacherSubjectRepositoryImpl implements TeacherSubjectRepository{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private TeacherRepositoryImpl teacherRepositoryImpl;
	@Autowired
	private SubjectRepositoryImpl subjectRepositoryImpl;
	@Override
	public ResponseEntity<String> assignTeacherSubject(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws TeacherNotFoundException, SubjectNotFoundException {
		// TODO Auto-generated method stub
		ResponseEntity<String> response=null;
		Session session=null;
		try
		{
			boolean checkTeacher=teacherRepositoryImpl.checkTeacher(teacherId);
			if(!checkTeacher)
			{
				throw new TeacherNotFoundException("Teacher Not Found with"+" "+teacherId+"!");
			}
			boolean checkSubject=subjectRepositoryImpl.checkSubject(subjectCode);
			if(!checkSubject)
			{
				throw new SubjectNotFoundException("Subject Not Found With"+" "+subjectCode+"!");
			}
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
			Teacher teacherDetails=new Teacher();
			teacherDetails.setId(teacherId);
			Subject subjectDetails=new Subject();
			subjectDetails.setSubCode(subjectCode);
			TeacherSubject teacherSubjectAssignDetails=new TeacherSubject();
			teacherSubjectAssignDetails.setTeacher(teacherDetails);
			teacherSubjectAssignDetails.setSubject(subjectDetails);
			session.save(teacherSubjectAssignDetails);
			//session.getTransaction().commit();
			response=new ResponseEntity<String>("Teacher is assigned for course successfully!",new HttpHeaders(),HttpStatus.OK);
		}
		catch(HibernateException |TeacherNotFoundException| SubjectNotFoundException e)
		{
			response=new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		
		return response;
	}
	@Override
	public ResponseEntity<String> updateTeacherSubjectAssign(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws TeacherNotFoundException, SubjectNotFoundException {
		// TODO Auto-generated method stub
		ResponseEntity<String> response=null;
		Session session=null;
		try
		{
			boolean checkTeacher=teacherRepositoryImpl.checkTeacher(teacherId);
			if(!checkTeacher)
			{
				throw new TeacherNotFoundException("Teacher Not Found with"+" "+teacherId+"!");
			}
			boolean checkSubject=subjectRepositoryImpl.checkSubject(subjectCode);
			if(!checkSubject)
			{
				throw new SubjectNotFoundException("Subject Not Found With"+" "+subjectCode+"!");
			}
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
			Query updateByTeacherId=session.createQuery("UPDATE TeacherSubject SET subCode=:code WHERE id=:staffId");
			updateByTeacherId.setParameter("code", subjectCode);
			updateByTeacherId.setParameter("staffId", teacherId);
			long countOfUpdationById=updateByTeacherId.executeUpdate();
			//session.getTransaction().commit();
			if(countOfUpdationById>0)
			{
				response=new ResponseEntity<String>("Details Updated Successfully!",new HttpHeaders(),HttpStatus.OK);
			}
		}
		catch(HibernateException |SubjectNotFoundException |TeacherNotFoundException e)
		{
			response=new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		return response;
	}
	@Override
	public ResponseEntity<String> deleteTeacherSubjectAssign(Long teacherId, String subjectCode)
			throws TeacherNotFoundException, SubjectNotFoundException {
		// TODO Auto-generated method stub
		ResponseEntity<String> response=null;
		Session session=null;
		try
		{
			boolean checkTeacher=teacherRepositoryImpl.checkTeacher(teacherId);
			if(!checkTeacher)
			{
				throw new TeacherNotFoundException("Teacher Not Found with"+" "+teacherId+"!");
			}
			boolean checkSubject=subjectRepositoryImpl.checkSubject(subjectCode);
			if(!checkSubject)
			{
				throw new SubjectNotFoundException("Subject Not Found With"+" "+subjectCode+"!");
			}
			session=sessionFactory.getCurrentSession();
			//session.beginTransaction();
			Query query=session.createQuery("DELETE FROM TeacherSubject WHERE id=:staffId AND subCode=:code");
			query.setParameter("staffId",teacherId );
			query.setParameter("code",subjectCode);
			long count=query.executeUpdate();
			//session.getTransaction().commit();
			if(count>0)
			{
				response=new ResponseEntity<String>("Details Deleted Successfully!",new HttpHeaders(),HttpStatus.OK);
			}
		}
		catch(HibernateException |SubjectNotFoundException |TeacherNotFoundException e)
		{
			response=new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.OK);
		}
		return response;
	}
	@Override
	public List<TeacherSubjectModel> getSubject(Long id) throws TeacherNotFoundException {
		List<TeacherSubjectModel> teacher;
		Session session=null;
			
		try {
			
			session=sessionFactory.getCurrentSession();
			boolean checkTeacher=teacherRepositoryImpl.checkTeacher(id);
			if(!checkTeacher)
			{
				throw new TeacherNotFoundException("Teacher Not Found with"+" "+id+"!");
			}
			else {
			Query query=session.createQuery("SELECT new com.questionaire.entity.TeacherSubjectModel"
					+ "(t.teacher.id,t.subject.subCode) "
					+ "FROM TeacherSubject t WHERE t.teacher.id=:id ");
			query.setParameter("id", id);
			teacher=query.getResultList();
			}
		}
		
//		catch(HibernateException e)
//		{
//			throw new DatabaseException(e.getMessage());
//		}
		finally
		{
			
		}
		
		return teacher;
	}

}
