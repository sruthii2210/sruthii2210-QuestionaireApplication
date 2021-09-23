package com.questionaire.repositoryimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;

import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.QuizIdNotFoundException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.mapper.QuizMapper;
import com.questionaire.repository.QuizRepository;

@Repository
@Transactional
public class QuizRepositoryImpl implements QuizRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SubjectRepositoryImpl sub;

	public void checkQuiz(Long id) throws QuizIdNotFoundException {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		Query<QuizEntity> query = session.createQuery("from QuizEntity where autoId=:id");
		query.setParameter("id", id);
		QuizEntity quiz = new QuizEntity();
		quiz = query.uniqueResultOptional().orElse(null);

		if (quiz == null)
			throw new QuizIdNotFoundException("Quiz Id not found!");

	}

	public void checkQuizBySubCode(String subCode) throws QuizIdNotFoundException {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from QuizEntity where subject.subCode=:subCode ");

		query.setParameter("subCode", subCode);
		List<QuizEntity> quiz;

		quiz = query.getResultList();

		if (quiz.isEmpty())
			throw new QuizIdNotFoundException("No quiz created for subject " + subCode + "!");

	}

	@Override
	public Long addQuiz(Long id, String subCode, Quiz quiz) throws DatabaseException {
		Session session = null;
		Long response = 0l;
		try {
			session = sessionFactory.getCurrentSession();
			Long quizId = (Long) session.save(QuizMapper.mapQuiz(id, subCode, quiz));
			if (quizId > 0) {
				response = quizId;
			}
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage());
		}

		return response;
	}

	@Override
	public List<QuizEntity> getQuiz(Long id, String subCode) throws DatabaseException {
		Session session = null;

		List<QuizEntity> quiz;
		try {
			session = sessionFactory.getCurrentSession();
			checkQuizBySubCode(subCode);
			Query query = session.createQuery("from QuizEntity  where teacher.id=:id and subject.subCode=:subCode");
			query.setParameter("subCode", subCode);
			query.setParameter("id", id);
			quiz = query.getResultList();
		} catch (HibernateException | QuizIdNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}

		return quiz;
	}

	@Override
	public List<QuizEntity> getQuizBySubCode(String subCode) throws DatabaseException {
		Session session = null;
		List<QuizEntity> quiz;
		try {
			session = sessionFactory.getCurrentSession();
			sub.checkSubject(subCode);
			Query query = session.createQuery("from QuizEntity  where subject.subCode=:subCode");
			query.setParameter("subCode", subCode);
			quiz = query.getResultList();
		} catch (HibernateException | SubjectNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}

		return quiz;
	}

}
