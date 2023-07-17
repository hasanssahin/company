package com.hasansahin.company.dto.converter;

import com.hasansahin.company.dto.create.EmployeeCreateDto;
import com.hasansahin.company.dto.view.EmployeeViewDto;
import com.hasansahin.company.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeConverter {
	public Employee convertEmployeeCreateDtoToEmployee(EmployeeCreateDto employeeCreateDto) {
		return new Employee(employeeCreateDto.getName(), employeeCreateDto.getSurname(), employeeCreateDto.getEmail());
	}

	public EmployeeViewDto convertEmployeeToEmployeeViewDto(Employee employee) {
		return new EmployeeViewDto(employee.getName(), employee.getSurname(), employee.getEmail());
	}

	public List<EmployeeViewDto> convertEmployeeToEmployeeViewDtoList(List<Employee> employees) {
		return employees.stream().map(this::convertEmployeeToEmployeeViewDto).toList();
	}

	public List<Employee> convertEmployeeCreateDtoToEmployeeList(List<EmployeeCreateDto> employeeCreateDtoList) {
		return employeeCreateDtoList.stream().map(this::convertEmployeeCreateDtoToEmployee).toList();
	}
}
