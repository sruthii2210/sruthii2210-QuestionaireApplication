package com.questionaire.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="result")
public class Result implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long autoId;
	
	@ManyToOne(targetEntity=Student.class,fetch=FetchType.LAZY)
	@JoinColumn(name="rollNo",nullable=false)
	@JsonIgnore
	private Student stud;
	
	@ManyToOne(targetEntity=SubjectEntity.class,fetch=FetchType.LAZY)
	@JoinColumn(name="subCode",nullable=false)
	@JsonIgnore
	private SubjectEntity sub;
	
	@ManyToOne(targetEntity=QuizEntity.class,fetch=FetchType.LAZY)
	@JoinColumn(name="id",nullable=false)
	@JsonIgnore
	private QuizEntity quiz;
	
	private Integer score;
	
	
}
