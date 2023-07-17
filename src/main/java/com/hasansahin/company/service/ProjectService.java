package com.hasansahin.company.service;

import com.hasansahin.company.dto.converter.EmployeeConverter;
import com.hasansahin.company.dto.converter.ProjectConverter;
import com.hasansahin.company.dto.create.ProjectCreateDto;
import com.hasansahin.company.dto.view.EmployeeViewDto;
import com.hasansahin.company.dto.view.ProjectViewDto;
import com.hasansahin.company.exception.ProjectNotFoundException;
import com.hasansahin.company.model.Employee;
import com.hasansahin.company.model.Project;
import com.hasansahin.company.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
	private final ProjectRepository projectRepository;
	private final ProjectConverter projectConverter;
	private final EmployeeConverter employeeConverter;
	private static final String MESSAGE = "Project not found";

	//Proje ekle
	public ProjectViewDto save(ProjectCreateDto projectCreateDto) {
		Project project = projectConverter.convertProjectCreateDtoToProject(projectCreateDto);
		project = projectRepository.save(project);
		return projectConverter.convertProjectToProjectViewDto(project);
	}

	//Projeleri ekle
	public List<ProjectViewDto> saveAll(List<ProjectCreateDto> projectCreateDtoList) {
		List<Project> projects = projectConverter.convertProjectCreateDtoToProjectList(projectCreateDtoList);
		return projectConverter.convertProjectToProjectViewDtoList(projectRepository.saveAll(projects));
	}

	//Proje güncelle
	public void updateProject(String projectUuid, ProjectCreateDto projectCreateDto) {
		Project project = projectRepository.findProjectByUuid(projectUuid).orElseThrow(() -> new ProjectNotFoundException(MESSAGE, HttpStatus.NOT_FOUND));
		project.setDescription(projectCreateDto.getDescription());
		project.setTitle(projectCreateDto.getTitle());
		projectRepository.save(project);
	}

	//proje sil
	@Transactional
	public void deleteProject(String projectUuid) {
		Project project = projectRepository.findProjectByUuid(projectUuid).orElseThrow(() -> new ProjectNotFoundException(MESSAGE, HttpStatus.NOT_FOUND));
		for (Employee employee : project.getEmployees()) {
			employee.setProjects(null);
		}
		projectRepository.deleteProjectByUuid(projectUuid);
	}

	//Projeleri al
	public List<ProjectViewDto> getAll() {
		return projectConverter.convertProjectToProjectViewDtoList(projectRepository.findAll());
	}

	//Projedeki çalışanları al
	public List<EmployeeViewDto> getEmployeesInProject(String projectUuid) {
		Project project = projectRepository.findProjectByUuid(projectUuid).orElseThrow(() -> new ProjectNotFoundException(MESSAGE, HttpStatus.NOT_FOUND));
		List<Employee> employeeList = project.getEmployees();
		return employeeConverter.convertEmployeeToEmployeeViewDtoList(employeeList);
	}
}
