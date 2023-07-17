package com.hasansahin.company.controller;

import com.hasansahin.company.dto.view.AddressViewDto;
import com.hasansahin.company.dto.view.EmployeeViewDto;
import com.hasansahin.company.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
	private final AddressService addressService;

	@GetMapping
	public ResponseEntity<List<AddressViewDto>> getAllAddresses() {
		return ResponseEntity.ok(addressService.getAll());
	}

	@GetMapping("/getEmployeesInCity")
	public ResponseEntity<List<EmployeeViewDto>> getEmployeesInCity(@RequestParam String city) {
		return ResponseEntity.ok(addressService.getEmployeesInCity(city));
	}
}
