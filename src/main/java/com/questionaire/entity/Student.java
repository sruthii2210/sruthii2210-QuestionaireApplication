package com.questionaire.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "Student")
public class Student implements Serializable{
	
	@ManyToOne(targetEntity=ClassRoom.class,fetch=FetchType.LAZY)
	@JoinColumn(name="roomNo",nullable=false)
	@JsonIgnore
	   private ClassRoom classRoom;
	
	@OneToMany(mappedBy="stud",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Result>results;
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Long rollNo;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	private String gender;
	private String address;
	//private Long roomNo;

	public Student(Long rollNo, String name, Date dateOfBirth, String gender, String address) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		

	}

}