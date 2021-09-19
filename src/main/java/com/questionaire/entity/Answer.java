package com.questionaire.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="answer")
public class Answer implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long autoId;
	
	@OneToOne(targetEntity=Question.class)
	@JoinColumn(name="quesNo")
	@JsonIgnore
	private Question ques;
	
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private Integer crctAns;
	
	
}
