package com.hasansahin.company.dto.converter;

import com.hasansahin.company.dto.create.AddressCreateDto;
import com.hasansahin.company.dto.view.AddressViewDto;
import com.hasansahin.company.model.Address;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressConverter {

	public Address convertAddressCreateDtoToAddress(AddressCreateDto addressCreateDto) {
		return new Address(addressCreateDto.getPostCode(), addressCreateDto.getCity(), addressCreateDto.getDistrict(), addressCreateDto.getStreet());
	}

	public AddressViewDto convertAddressToAddressViewDto(Address address) {
		return new AddressViewDto(address.getPostCode(), address.getCity(), address.getDistrict(), address.getStreet(), address.getUuid());
	}

	public List<AddressViewDto> convertAddressToAddressViewDtoList(List<Address> addressList) {
		return addressList.stream().map(this::convertAddressToAddressViewDto).toList();
	}
}
