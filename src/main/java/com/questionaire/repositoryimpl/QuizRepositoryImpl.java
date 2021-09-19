package com.questionaire.repositoryimpl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questionaire.entity.ClassRoom;
import com.questionaire.entity.Quiz;
import com.questionaire.entity.Student;
import com.questionaire.entity.Subject;
import com.questionaire.entity.Teacher;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.repository.QuizRepository;

@Repository
@Transactional
public class QuizRepositoryImpl implements QuizRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SubjectRepositoryImpl sub;
	
	public boolean checkQuiz(Long id) throws QuizIdNotFoundException {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Question where quiz.id=:id");
		query.setParameter("id", id);
		Object question = null;
		try {
			question = query.getSingleResult();
		} catch (NoResultException e) {

		}
		if (question == null)
			throw new QuizIdNotFoundException("Quiz Id not found!");
		return true;
	}

	public boolean checkQuizBySubCode(Long id, String subCode) throws QuizIdNotFoundException {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Quiz where autoId=:id and subject.subCode=:subCode ");
		query.setParameter("id", id);
		query.setParameter("subCode", subCode);
		Object quiz = null;
		try {
			quiz = query.getSingleResult();
		} catch (NoResultException e) {

		}
		if (quiz == null)
			throw new QuizIdNotFoundException("No quiz created for subject " + subCode + " and quizId " + id + "!");
		return true;
	}

	@Override
	public Quiz addQuiz(Long id, String subCode, Quiz quiz) throws DatabaseException {
		Session session = null;
		Quiz response = null;
		try {
			session = sessionFactory.getCurrentSession();
			Subject sub = new Subject();
			sub.setSubCode(subCode);
			Teacher t = new Teacher();
			t.setId(id);
			Quiz q = new Quiz();
			q.setName(quiz.getName());
			q.setTeacher(t);
			q.setSubject(sub);
			session.save(q);
			Long count = (Long) session.save(q);
			if (count > 0) {
				response = q;
			}
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public List<Quiz> getQuiz(Long id, String subCode) throws DatabaseException {
		Session session = null;
		// sub = new SubjectRepositoryImpl();
		List<Quiz> quiz;
		try {
			session = sessionFactory.getCurrentSession();
			boolean status = checkQuizBySubCode(id, subCode);
			Query query = session.createQuery("from Quiz  where teacher.id=:id and subject.subCode=:subCode  ");
			query.setParameter("subCode", subCode);
			query.setParameter("id", id);
			quiz = query.getResultList();
		} catch (HibernateException | QuizIdNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}

		return quiz;
	}

	@Override
	public List<Quiz> getQuizBySubCode(String subCode) throws DatabaseException {
		Session session = null;
		List<Quiz> quiz;
		try {
			session = sessionFactory.getCurrentSession();
			boolean status = sub.checkSubject(subCode);
			Query query = session.createQuery("from Quiz  where subject.subCode=:subCode");
			query.setParameter("subCode", subCode);
			quiz = query.getResultList();
		} catch (HibernateException | SubjectNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}

		return quiz;
	}

}
