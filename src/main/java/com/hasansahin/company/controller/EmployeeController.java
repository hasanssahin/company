package com.hasansahin.company.controller;

import com.hasansahin.company.dto.create.AddressCreateDto;
import com.hasansahin.company.dto.create.EmployeeCreateDto;
import com.hasansahin.company.dto.view.EmployeeViewDto;
import com.hasansahin.company.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	private final EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<EmployeeViewDto> createEmployee(@RequestBody EmployeeCreateDto employeeCreateDto) {
		return ResponseEntity.ok(employeeService.save(employeeCreateDto));
	}

	@PostMapping("/saveAll")
	public ResponseEntity<List<EmployeeViewDto>> saveAllEmployees(@RequestBody List<EmployeeCreateDto> employeeCreateDtoList) {
		return ResponseEntity.ok(employeeService.saveAll(employeeCreateDtoList));
	}

	@PutMapping("/addAddress")
	public ResponseEntity<Void> addAddress(@RequestParam String email, @RequestBody AddressCreateDto addressCreateDto) {
		employeeService.addAddress(email, addressCreateDto);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/addDepartment")
	public ResponseEntity<Void> addDepartment(@RequestParam String email, @RequestParam String departmentUuid) {
		employeeService.addDepartment(email, departmentUuid);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/addProject")
	public ResponseEntity<Void> addProject(@RequestParam String email, @RequestParam String projectUuid) {
		employeeService.addProject(email, projectUuid);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/updateAddress")
	public ResponseEntity<Void> updateAddress(@RequestParam String email, @RequestBody AddressCreateDto addressCreateDto) {
		employeeService.updateEmployeeAddress(email, addressCreateDto);
		return ResponseEntity.noContent().build();
	}


	@DeleteMapping
	public ResponseEntity<Void> deleteEmployee(@RequestParam String email) {
		employeeService.delete(email);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<EmployeeViewDto>> getAllEmployee() {
		return ResponseEntity.ok(employeeService.getAll());
	}


}
