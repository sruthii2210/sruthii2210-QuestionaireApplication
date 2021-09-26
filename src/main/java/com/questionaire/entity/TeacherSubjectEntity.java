package com.questionaire.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "TeacherSubject")
public class TeacherSubjectEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long autoId;

	@ManyToOne(targetEntity = TeacherEntity.class)
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private TeacherEntity teacher;

	@ManyToOne(targetEntity = SubjectEntity.class)
	@JoinColumn(name = "code", nullable = false)
	@JsonIgnore
	private SubjectEntity subject;
	
	@ManyToOne(targetEntity = ClassRoom.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "roomNo", nullable = false)
	//@JsonIgnore
	private ClassRoom classRoom;
}
