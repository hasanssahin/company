package com.hasansahin.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String uuid;

	@OneToMany(mappedBy = "department")
	@JsonIgnore
	private List<Employee> employees = new ArrayList<>();

	public Department(String name) {
		this.name = name;
		this.uuid = UUID.randomUUID().toString();
	}
}
