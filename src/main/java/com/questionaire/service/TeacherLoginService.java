package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.TeacherLogin;

public interface TeacherLoginService {

	ResponseEntity<String> createLogin( Long id,TeacherLogin login);
	List<TeacherLogin> getDetails(Long id);
	ResponseEntity<String> updateLogin(Long id,TeacherLogin login);
	
	

}
