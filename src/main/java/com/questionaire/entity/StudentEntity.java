package com.questionaire.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
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
public class StudentEntity implements Serializable{
	
	@ManyToOne(targetEntity=ClassRoom.class,fetch=FetchType.LAZY)
	@JoinColumn(name="roomNo",nullable=false)
	@JsonIgnore
	   private ClassRoom classRoom;
	
	@OneToMany(mappedBy="student",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<ResultEntity>results;
	
	@Id
	private Long rollNo;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	@Column(nullable=false)
	private String gender;
	@Column(nullable=false)
	private String address;
	

	public StudentEntity(Long rollNo, String name, Date dateOfBirth, String gender, String address) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		

	}

}