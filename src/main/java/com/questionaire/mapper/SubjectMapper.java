package com.questionaire.mapper;

import com.questionaire.dto.Subject;
import com.questionaire.entity.SubjectEntity;

public class SubjectMapper {

	public static SubjectEntity mapSubject(Subject subject,String standard) {
		
		
		SubjectEntity subjectEntity=new SubjectEntity();
		subjectEntity.setCode(subject.getCode());
		subjectEntity.setName(subject.getName());
		subjectEntity.setStandard(standard);
	
			return subjectEntity;
}
}
