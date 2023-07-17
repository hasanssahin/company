package com.hasansahin.company.service;

import com.hasansahin.company.dto.converter.DepartmentConverter;
import com.hasansahin.company.dto.converter.EmployeeConverter;
import com.hasansahin.company.dto.create.DepartmentCreateDto;
import com.hasansahin.company.dto.view.DepartmentViewDto;
import com.hasansahin.company.dto.view.EmployeeViewDto;
import com.hasansahin.company.exception.DepartmentNotFoundException;
import com.hasansahin.company.model.Department;
import com.hasansahin.company.model.Employee;
import com.hasansahin.company.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final DepartmentConverter departmentConverter;
	private final EmployeeConverter employeeConverter;
	private static final String MESSAGE = "Department not found";

	//Departman ekle
	public DepartmentViewDto save(DepartmentCreateDto departmentCreateDto) {
		Department department = departmentConverter.convertDepartmentCreateDtoToDepartment(departmentCreateDto);
		department = departmentRepository.save(department);
		return departmentConverter.convertDepartmentToDepartmentViewDto(department);
	}

	//Departmanları ekle
	public List<DepartmentViewDto> saveAll(List<DepartmentCreateDto> departmentCreateDtoList) {
		List<Department> departments = departmentConverter.convertDepartmentCreateDtoToDepartmentList(departmentCreateDtoList);
		return departmentConverter.convertDepartmentToDepartmentViewDtoList(departmentRepository.saveAll(departments));
	}

	//Departman güncelle
	public void updateDepartment(String departmentUuid, DepartmentCreateDto departmentCreateDto) {
		Department department = departmentRepository.findDepartmentByUuid(departmentUuid).orElseThrow(() -> new DepartmentNotFoundException(MESSAGE, HttpStatus.NOT_FOUND));
		department.setName(departmentCreateDto.getName());
		departmentRepository.save(department);
	}

	//Departman sil
	@Transactional
	public void deleteDepartment(String departmentUuid) {
		Department department = departmentRepository.findDepartmentByUuid(departmentUuid).orElseThrow(() -> new DepartmentNotFoundException(MESSAGE, HttpStatus.NOT_FOUND));
		for (Employee employee : department.getEmployees()) {
			employee.setDepartment(null);
		}
		departmentRepository.deleteDepartmentByUuid(departmentUuid);
	}

	//... departmanındaki kişileri al
	public List<EmployeeViewDto> getEmployeesInDepartment(String departmentUuid) {
		Department department = departmentRepository.findDepartmentByUuid(departmentUuid).orElseThrow(() -> new DepartmentNotFoundException(MESSAGE, HttpStatus.NOT_FOUND));
		List<Employee> employeeList = department.getEmployees();
		return employeeConverter.convertEmployeeToEmployeeViewDtoList(employeeList);
	}

	//Departmanları listele
	public List<DepartmentViewDto> getAll() {
		return departmentConverter.convertDepartmentToDepartmentViewDtoList(departmentRepository.findAll());
	}
}
