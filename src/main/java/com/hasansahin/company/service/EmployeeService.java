package com.hasansahin.company.service;

import com.hasansahin.company.dto.converter.AddressConverter;
import com.hasansahin.company.dto.converter.EmployeeConverter;
import com.hasansahin.company.dto.create.AddressCreateDto;
import com.hasansahin.company.dto.create.EmployeeCreateDto;
import com.hasansahin.company.dto.view.EmployeeViewDto;
import com.hasansahin.company.exception.DepartmentNotFoundException;
import com.hasansahin.company.exception.EmployeeNotFoundException;
import com.hasansahin.company.exception.ProjectNotFoundException;
import com.hasansahin.company.model.Address;
import com.hasansahin.company.model.Department;
import com.hasansahin.company.model.Employee;
import com.hasansahin.company.model.Project;
import com.hasansahin.company.repository.DepartmentRepository;
import com.hasansahin.company.repository.EmployeeRepository;
import com.hasansahin.company.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeConverter employeeConverter;
	private final AddressConverter addressConverter;
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final ProjectRepository projectRepository;

	private static final String MESSAGE = "Employee not found";


	//Çalışan oluştur
	public EmployeeViewDto save(EmployeeCreateDto employeeCreateDto) {
		Employee employee = employeeConverter.convertEmployeeCreateDtoToEmployee(employeeCreateDto);
		return employeeConverter.convertEmployeeToEmployeeViewDto(employeeRepository.save(employee));
	}

	//Toplu çalışan oluştur
	public List<EmployeeViewDto> saveAll(List<EmployeeCreateDto> employeeCreateDtoList) {
		List<Employee> employees = employeeConverter.convertEmployeeCreateDtoToEmployeeList(employeeCreateDtoList);
		return employeeConverter.convertEmployeeToEmployeeViewDtoList(employeeRepository.saveAll(employees));
	}

	//Çalışana ilk adresi ekle
	public void addAddress(String email, AddressCreateDto addressCreateDto) {
		Employee employee = employeeRepository.findEmployeeByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(MESSAGE, HttpStatus.NOT_FOUND));
		Address address = addressConverter.convertAddressCreateDtoToAddress(addressCreateDto);
		employee.setAddress(address);
		employeeRepository.save(employee);
	}

	//Çalışana departman ekle
	public void addDepartment(String email, String departmentUuid) {
		Employee employee = employeeRepository.findEmployeeByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(MESSAGE, HttpStatus.NOT_FOUND));
		Department department = departmentRepository.findDepartmentByUuid(departmentUuid).orElseThrow(() -> new DepartmentNotFoundException("Department not found", HttpStatus.NOT_FOUND));
		employee.setDepartment(department);
		department.getEmployees().add(employee);
		employeeRepository.save(employee);
	}

	//Çalışana proje ekle
	public void addProject(String email, String projectUuid) {
		Employee employee = employeeRepository.findEmployeeByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(MESSAGE, HttpStatus.NOT_FOUND));
		Project project = projectRepository.findProjectByUuid(projectUuid).orElseThrow(() -> new ProjectNotFoundException("Project not found", HttpStatus.NOT_FOUND));
		employee.getProjects().add(project);
		project.getEmployees().add(employee);
		employeeRepository.save(employee);
	}

	//Çalışanın adresini güncelle
	public void updateEmployeeAddress(String email, AddressCreateDto addressCreateDto) {
		Employee employee = employeeRepository.findEmployeeByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(MESSAGE, HttpStatus.NOT_FOUND));
		employee.getAddress().setPostCode(addressCreateDto.getPostCode());
		employee.getAddress().setCity(addressCreateDto.getCity());
		employee.getAddress().setDistrict(addressCreateDto.getDistrict());
		employee.getAddress().setStreet(addressCreateDto.getStreet());
		employeeRepository.save(employee);
	}


	//Çalışanı silme
	@Transactional
	public void delete(String email) {
		Employee employee = employeeRepository.findEmployeeByEmail(email).orElseThrow(() -> new ProjectNotFoundException(MESSAGE, HttpStatus.NOT_FOUND));
		employee.setDepartment(null);
		employee.getProjects().clear();
		employeeRepository.deleteEmployeeByEmail(email);
	}

	//Çalışanları listeleme
	public List<EmployeeViewDto> getAll() {
		return employeeConverter.convertEmployeeToEmployeeViewDtoList(employeeRepository.findAll());
	}

}
