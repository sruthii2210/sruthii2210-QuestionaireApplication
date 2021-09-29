package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionaire.dto.ClassDetails;
import com.questionaire.entity.ClassRoom;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.ClassRepository;
import com.questionaire.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassRepository classRepositoryImp;

	public Long addClass(ClassDetails classDetails) throws ServiceException {
		try {
			return classRepositoryImp.addClass(classDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<ClassRoom> getClassDetails() throws ServiceException {
		try {
			return classRepositoryImp.getClassDetails();
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public ClassRoom updateClass(Long roomNo, ClassDetails classDetails) throws ServiceException, NotFoundException {
		try {
			classRepositoryImp.checkClassRoomNo(roomNo);
			return classRepositoryImp.updateClass(roomNo, classDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public ClassRoom getClass(String standard, String section) throws ServiceException {
		ClassRoom cls;
		try {
			cls = classRepositoryImp.getClass(standard, section);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		return cls;
	}

	@Override
	public ClassRoom getClassDetails(Long roomNo) throws ServiceException, NotFoundException {
		try {
			classRepositoryImp.checkClassRoomNo(roomNo);
			return classRepositoryImp.getClassDetails(roomNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
