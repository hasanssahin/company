package com.hasansahin.company.repository;

import com.hasansahin.company.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	Optional<Project> findProjectByUuid(String uuid);

	void deleteProjectByUuid(String uuid);
}
