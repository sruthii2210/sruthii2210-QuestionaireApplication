package com.questionaire.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="class")
public class ClassRoom implements Serializable{
	
	
	@OneToMany(mappedBy="classRoom",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Student> students;
	
	@OneToMany(mappedBy="classRoom",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Subject> subjects;
	
	@Id
	@Column(nullable=false)
	private Long roomNo;
	@Column(nullable=false)
	private String standard;
	@Column(nullable=false)
	private String section;
	public ClassRoom( Long roomNo, String standard, String section) {
		super();
		this.roomNo = roomNo;
		this.standard = standard;
		this.section = section;
	}
	
	
	
	
	

}
