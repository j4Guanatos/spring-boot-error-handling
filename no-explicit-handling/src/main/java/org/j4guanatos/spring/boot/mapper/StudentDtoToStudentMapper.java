package org.j4guanatos.spring.boot.mapper;

import org.j4guanatos.spring.boot.dto.AddressDto;
import org.j4guanatos.spring.boot.dto.StudentDto;
import org.j4guanatos.spring.boot.model.Address;
import org.j4guanatos.spring.boot.model.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoToStudentMapper implements Mapper<StudentDto, Student> {

	@Autowired
	private Mapper<AddressDto, Address> addressMapper;

	@Override
	public Student toModel(StudentDto dto) {
		Student s = new Student();
		BeanUtils.copyProperties(dto, s, "address", "created", "updated");
		s.setAddress(addressMapper.toModel(dto.getAddress()));
		return s;
	}

	@Override
	public StudentDto toDto(Student model) {
		StudentDto s = new StudentDto();
		BeanUtils.copyProperties(model, s, "address", "created", "updated");
		s.setAddress(addressMapper.toDto(model.getAddress()));
		return s;
	}

}
