package com.hasansahin.company.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String surname;
	private String email;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Address address;

	@ManyToOne(cascade = CascadeType.ALL)
	private Department department;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Project> projects = new ArrayList<>();

	public Employee(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
}
