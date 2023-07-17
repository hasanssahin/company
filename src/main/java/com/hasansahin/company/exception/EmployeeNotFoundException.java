package com.hasansahin.company.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class EmployeeNotFoundException extends RuntimeException {
	private final String message;
	private final HttpStatus status;
}
