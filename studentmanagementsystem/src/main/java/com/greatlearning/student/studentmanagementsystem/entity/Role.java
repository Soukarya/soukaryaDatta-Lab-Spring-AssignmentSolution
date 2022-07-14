package com.greatlearning.student.studentmanagementsystem.entity;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private int role_id;
	
	@Column(name="name")
	private String name;
}
