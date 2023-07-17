package com.hasansahin.company.dto.converter;

import com.hasansahin.company.dto.create.ProjectCreateDto;
import com.hasansahin.company.dto.view.ProjectViewDto;
import com.hasansahin.company.model.Project;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectConverter {
	public Project convertProjectCreateDtoToProject(ProjectCreateDto projectCreateDto) {
		return new Project(projectCreateDto.getTitle(), projectCreateDto.getDescription());
	}

	public ProjectViewDto convertProjectToProjectViewDto(Project project) {
		return new ProjectViewDto(project.getTitle(), project.getDescription(), project.getUuid());
	}

	public List<ProjectViewDto> convertProjectToProjectViewDtoList(List<Project> projectList) {
		return projectList.stream().map(this::convertProjectToProjectViewDto).toList();
	}

	public List<Project> convertProjectCreateDtoToProjectList(List<ProjectCreateDto> projectCreateDtoList) {
		return projectCreateDtoList.stream().map(this::convertProjectCreateDtoToProject).toList();
	}
}
