package com.hasansahin.company.controller;

import com.hasansahin.company.dto.create.ProjectCreateDto;
import com.hasansahin.company.dto.view.EmployeeViewDto;
import com.hasansahin.company.dto.view.ProjectViewDto;
import com.hasansahin.company.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
	private final ProjectService projectService;

	@PostMapping
	public ResponseEntity<ProjectViewDto> createProject(@RequestBody ProjectCreateDto projectCreateDto) {
		return ResponseEntity.ok(projectService.save(projectCreateDto));
	}

	@PostMapping("/saveAll")
	public ResponseEntity<List<ProjectViewDto>> saveAllProjects(@RequestBody List<ProjectCreateDto> projectCreateDtoList) {
		return ResponseEntity.ok(projectService.saveAll(projectCreateDtoList));
	}

	@GetMapping
	public ResponseEntity<List<ProjectViewDto>> getAllProject() {
		return ResponseEntity.ok(projectService.getAll());
	}

	@GetMapping("/getEmployeesInProject")
	public ResponseEntity<List<EmployeeViewDto>> getEmployeesInProject(@RequestParam String projectUuid) {
		return ResponseEntity.ok(projectService.getEmployeesInProject(projectUuid));
	}

	@PutMapping("/update")
	public ResponseEntity<Void> updateProject(@RequestParam String projectUuid, @RequestBody ProjectCreateDto projectCreateDto) {
		projectService.updateProject(projectUuid, projectCreateDto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteProject(@RequestParam String projectUuid) {
		projectService.deleteProject(projectUuid);
		return ResponseEntity.noContent().build();
	}

}
