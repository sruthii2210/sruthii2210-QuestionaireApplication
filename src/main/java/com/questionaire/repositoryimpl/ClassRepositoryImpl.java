package com.questionaire.repositoryimpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.questionaire.dto.ClassDetails;
import com.questionaire.entity.ClassRoom;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.mapper.ClassMapper;
import com.questionaire.repository.ClassRepository;

@Repository
@Transactional
public class ClassRepositoryImpl implements ClassRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void checkClassRoomNo(Long roomNo) throws RoomNoNotFoundException {
		ClassRoom classDetail= new ClassRoom();
	 
	      Session session =sessionFactory.getCurrentSession();
	      Query query=session.createQuery("from ClassRoom where roomNo=:roomNo");
	      query.setParameter("roomNo",roomNo);
	     // classDetail=(ClassRoom) query.getSingleResult();
	      try {
	    	  classDetail = (ClassRoom) query.getSingleResult();
			} catch (NoResultException e) {

			}
	      if(classDetail==null)
	      {
	    	  throw new RoomNoNotFoundException("Class not Found,Enter the Valid Room No!");
	      }
	      
	  }
	
	public Long addClass(ClassDetails classDetails) throws DatabaseException
	{
		Session session=null;
		Long response=null;
		try {
			session=sessionFactory.getCurrentSession();
			Long count = (Long) session.save(ClassMapper.mapClassRoom(classDetails));
			
			if(count>0)
			{
				response = count;
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
		List<ClassRoom> classDetails=null;
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
	public ClassRoom updateClass(Long roomNo,ClassDetails classDetails) throws DatabaseException{
		Session session=null;
		ClassRoom updatedClass=null;
		
		try {
			session=sessionFactory.getCurrentSession();
			
			ClassRoom classRoom=ClassMapper.mapClassRoom(classDetails);
			session.find(ClassRoom.class, roomNo);
			ClassRoom clsRoom=session.load(ClassRoom.class, roomNo);
			
			clsRoom.setSection(classDetails.getSection());
			clsRoom.setStandard(classDetails.getStandard());
			updatedClass=(ClassRoom) session.merge(clsRoom);
			
			
			}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		
		return updatedClass;
	}
	@Override
	public ClassRoom getClass(String standard, String section) throws DatabaseException {
		ClassRoom classRoom = null;
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			Query<ClassRoom> query=session.createQuery("from ClassRoom c where c.standard=:standard and c.section=:section");
			query.setParameter("standard", standard);
			query.setParameter("section", section);
			try {
		    	  classRoom = (ClassRoom) query.getSingleResult();
				} catch (NoResultException e) {

				}
		}
		catch(HibernateException e)
		{
			throw new DatabaseException(e.getMessage());
		}
		return classRoom;
	}

}
