package com.hasansahin.company.dto.converter;

import com.hasansahin.company.dto.create.DepartmentCreateDto;
import com.hasansahin.company.dto.view.DepartmentViewDto;
import com.hasansahin.company.model.Department;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentConverter {
	public Department convertDepartmentCreateDtoToDepartment(DepartmentCreateDto departmentCreateDto) {
		return new Department(departmentCreateDto.getName());
	}

	public DepartmentViewDto convertDepartmentToDepartmentViewDto(Department department) {
		return new DepartmentViewDto(department.getName(), department.getUuid());
	}

	public List<DepartmentViewDto> convertDepartmentToDepartmentViewDtoList(List<Department> departmentList) {
		return departmentList.stream().map(this::convertDepartmentToDepartmentViewDto).toList();
	}

	public List<Department> convertDepartmentCreateDtoToDepartmentList(List<DepartmentCreateDto> departmentCreateDtoList) {
		return departmentCreateDtoList.stream().map(this::convertDepartmentCreateDtoToDepartment).toList();
	}

}
