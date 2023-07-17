package com.hasansahin.company.service;

import com.hasansahin.company.dto.converter.AddressConverter;
import com.hasansahin.company.dto.converter.EmployeeConverter;
import com.hasansahin.company.dto.view.AddressViewDto;
import com.hasansahin.company.dto.view.EmployeeViewDto;
import com.hasansahin.company.exception.AddressNotFoundException;
import com.hasansahin.company.model.Address;
import com.hasansahin.company.model.Employee;
import com.hasansahin.company.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {
	private final AddressRepository addressRepository;
	private final AddressConverter addressConverter;
	private final EmployeeConverter employeeConverter;

	//Adresleri al
	public List<AddressViewDto> getAll() {
		return addressConverter.convertAddressToAddressViewDtoList(addressRepository.findAll());
	}

	//... şehrinde olan çalışanları al
	public List<EmployeeViewDto> getEmployeesInCity(String city) {
		List<Address> addressList = addressRepository.findAllByCity(city).orElseThrow(() -> new AddressNotFoundException("Address not found", HttpStatus.NOT_FOUND));
		List<Employee> employeeList = new ArrayList<>();
		addressList.forEach(address -> employeeList.add(address.getEmployee()));
		return employeeConverter.convertEmployeeToEmployeeViewDtoList(employeeList);
	}
}
