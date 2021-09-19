package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.entity.TeacherLogin;
import com.questionaire.repository.TeacherLoginRepository;
import com.questionaire.service.TeacherLoginService;

@Service
public class TeacherLoginServiceImpl implements TeacherLoginService {

	@Autowired
	private TeacherLoginRepository teacherLoginRepository;
	public ResponseEntity<String> createLogin(Long id,TeacherLogin login)
	{
		return teacherLoginRepository.createLogin(id,login);
	}
	@Override
	public List<TeacherLogin> getDetails(Long id) {
		
		List<TeacherLogin> teacher=teacherLoginRepository.getDetails(id);
		return teacher;
	}
	@Override
	public ResponseEntity<String> updateLogin(Long id,TeacherLogin login) {
		return teacherLoginRepository.updateLogin(id,login);
	}

}
