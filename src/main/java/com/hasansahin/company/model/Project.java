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
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String uuid;
	@Lob
	@Column(columnDefinition = "text")
	private String description;

	@ManyToMany(mappedBy = "projects")
	@JsonIgnore
	private List<Employee> employees = new ArrayList<>();

	public Project(String title, String description) {
		this.title = title;
		this.description = description;
		this.uuid = UUID.randomUUID().toString();
	}
}
