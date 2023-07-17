package com.hasansahin.company.dto.create;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressCreateDto {
	@NotBlank
	private String postCode;
	@NotBlank
	private String city;
	@NotBlank
	private String district;
	@NotBlank
	private String street;
}
