package com.hasansahin.company.repository;

import com.hasansahin.company.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Optional<Department> findDepartmentByUuid(String uuid);

	void deleteDepartmentByUuid(String uuid);
}