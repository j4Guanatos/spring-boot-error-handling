package org.j4guanatos.spring.boot.mapper;

import org.j4guanatos.spring.boot.dto.AddressDto;
import org.j4guanatos.spring.boot.model.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToAddressMapper implements Mapper<AddressDto, Address> {

	@Override
	public Address toModel(AddressDto dto) {
		Address a = new Address();
		BeanUtils.copyProperties(dto, a);
		return a;
	}

	@Override
	public AddressDto toDto(Address model) {
		AddressDto a = new AddressDto();
		BeanUtils.copyProperties(model, a);
		return a;
	}

}
