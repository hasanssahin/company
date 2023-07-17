package com.hasansahin.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String postCode;
	private String city;
	private String district;
	private String street;
	private String uuid;

	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private Employee employee;

	public Address(String postCode, String city, String district, String street) {
		this.postCode = postCode;
		this.city = city;
		this.district = district;
		this.street = street;
		this.uuid = UUID.randomUUID().toString();
	}
}
