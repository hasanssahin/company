package com.hasansahin.company.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressViewDto {
	private String postCode;
	private String city;
	private String district;
	private String street;
	private String uuid;
}
