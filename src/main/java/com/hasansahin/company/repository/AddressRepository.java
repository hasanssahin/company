package com.hasansahin.company.repository;

import com.hasansahin.company.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
	Optional<List<Address>> findAllByCity(String city);
}
