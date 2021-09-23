package com.questionaire.entity;

import java.io.Serializable;

import javax.persistence.Entity;
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
@Table(name="TeacherSubject")
public class TeacherSubject implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long autoId;
	
	@ManyToOne(targetEntity=Teacher.class)
	@JoinColumn(name="id",nullable=false)
	@JsonIgnore
	private Teacher teacher;
	
	@ManyToOne(targetEntity=SubjectEntity.class)
	@JoinColumn(name="subCode",nullable=false,unique=true)
	@JsonIgnore
	private SubjectEntity subject;
}
