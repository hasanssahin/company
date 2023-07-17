package com.hasansahin.company.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> employeeNotFoundException(EmployeeNotFoundException employeeNotFoundException) {
		return new ResponseEntity<>(employeeNotFoundException.getMessage(), employeeNotFoundException.getStatus());
	}

	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<String> departmentNotFoundException(DepartmentNotFoundException departmentNotFoundException) {
		return new ResponseEntity<>(departmentNotFoundException.getMessage(), departmentNotFoundException.getStatus());
	}

	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<String> projectNotFoundException(ProjectNotFoundException projectNotFoundException) {
		return new ResponseEntity<>(projectNotFoundException.getMessage(), projectNotFoundException.getStatus());
	}

	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<String> addressNotFoundException(AddressNotFoundException addressNotFoundException) {
		return new ResponseEntity<>(addressNotFoundException.getMessage(), addressNotFoundException.getStatus());
	}
}
