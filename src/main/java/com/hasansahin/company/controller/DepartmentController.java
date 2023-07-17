package com.hasansahin.company.controller;

import com.hasansahin.company.dto.create.DepartmentCreateDto;
import com.hasansahin.company.dto.view.DepartmentViewDto;
import com.hasansahin.company.dto.view.EmployeeViewDto;
import com.hasansahin.company.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
	private final DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<DepartmentViewDto> createDepartment(@RequestBody DepartmentCreateDto departmentCreateDto) {
		return ResponseEntity.ok(departmentService.save(departmentCreateDto));
	}

	@PostMapping("/saveAll")
	public ResponseEntity<List<DepartmentViewDto>> saveAllDepartment(@RequestBody List<DepartmentCreateDto> departmentCreateDtoList) {
		return ResponseEntity.ok(departmentService.saveAll(departmentCreateDtoList));
	}

	@GetMapping
	public ResponseEntity<List<DepartmentViewDto>> getAllDepartment() {
		return ResponseEntity.ok(departmentService.getAll());
	}

	@PutMapping("/update")
	public ResponseEntity<Void> updateDepartment(@RequestParam String departmentUuid, @RequestBody DepartmentCreateDto departmentCreateDto) {
		departmentService.updateDepartment(departmentUuid, departmentCreateDto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteDepartment(@RequestParam String departmentUuid) {
		departmentService.deleteDepartment(departmentUuid);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/getEmployeesInDepartment")
	public ResponseEntity<List<EmployeeViewDto>> getEmployeesInDepartment(@RequestParam String departmentUuid) {
		return ResponseEntity.ok(departmentService.getEmployeesInDepartment(departmentUuid));
	}
}
