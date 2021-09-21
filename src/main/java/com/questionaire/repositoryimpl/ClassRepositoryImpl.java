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
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.repository.ClassRepository;

@Repository
@Transactional
public class ClassRepositoryImpl implements ClassRepository{

	@Autowired
	private SessionFactory sessionFactory;
	public boolean checkClassRoomNo(Long roomNo) throws RoomNoNotFoundException {
		  ClassRoom classDetail=new ClassRoom();
	      boolean status = false;
	      Session session =sessionFactory.getCurrentSession();
	      Query query=session.createQuery("from ClassRoom where roomNo=:roomNo");
	      query.setParameter("roomNo",roomNo);
	      classDetail=(ClassRoom) query.getSingleResult();
	      
	      if(classDetail==null)
	      {
	    	  throw new RoomNoNotFoundException("Class not Found,Enter the Valid Room No!");
	      }
	      return status;
	  }
	
	public ClassRoom addClass(ClassRoom classDetails) throws DatabaseException
	{
		Session session=null;
		ClassRoom response=null;
		try {
			session=sessionFactory.getCurrentSession();
			session.save(classDetails);
			Long count = (Long) session.save(classDetails);
			
			if(count>0)
			{
				response = classDetails;
			}
			}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return response;
		
	}
	@Override
	public List<ClassRoom> getClassDetails() throws DatabaseException {
		List<ClassRoom> classDetails=new ArrayList<ClassRoom>();
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from ClassRoom c");
			classDetails=query.getResultList();
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return classDetails;
	}
	@Override
	public ClassRoom updateClass(Long roomNo,ClassRoom classDetails) throws DatabaseException {
		Session session=null;
		ClassRoom response=null;
		ClassRoom updatedClass=null;
		try {
			session=sessionFactory.getCurrentSession();
			session.find(ClassRoom.class, roomNo);
			ClassRoom cls=session.load(ClassRoom.class, roomNo);
			cls.setSection(classDetails.getSection());
			cls.setStandard(classDetails.getStandard());
			updatedClass=(ClassRoom) session.merge(cls);
			
			response=updatedClass;
			}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return response;
	}
	@Override
	public ClassRoom getClass(String standard, String section) throws DatabaseException {
		ClassRoom classDetails;
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from ClassRoom c where c.standard=:standard and c.section=:section");
			query.setParameter("standard", standard);
			query.setParameter("section", section);
			classDetails=(ClassRoom) query.getSingleResult();
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return classDetails;
	}

}
