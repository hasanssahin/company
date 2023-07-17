package com.hasansahin.company.repository;

import com.hasansahin.company.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findEmployeeByEmail(String email);

	void deleteEmployeeByEmail(String email);
}
