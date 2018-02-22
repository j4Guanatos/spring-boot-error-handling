package org.j4guanatos.spring.boot.dto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

	@NotNull
	@Min(1)
	@Max(Long.MAX_VALUE)
	private Long id;

	@NotNull
	@Size(min = 1)
	private String firstName;

	@NotNull
	@Size(min = 1)
	private String lastName;

	@NotNull
	@Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birthdate;

	@Pattern(regexp = "^[0-9\\+]{1,}[0-9\\- ]{3,15}$")
	private String phoneNumber;

	@NotNull
	private String email;

	@Valid
	private AddressDto address;

	@NotNull
	@Size(min = 3, max = 10)
	private List<Long> subjects;

	@Min(0)
	@Max(100)
	private BigDecimal grade;

	private final static MathContext ROUND_DETAILS = new MathContext(2);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public List<Long> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Long> subjects) {
		this.subjects = subjects;
	}

	public BigDecimal getGrade() {
		return grade;
	}

	public void setGrade(BigDecimal grade) {
		if (grade != null) {
			this.grade = grade.round(ROUND_DETAILS);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(firstName).append(lastName).append(birthdate).append(phoneNumber)
				.append(email).append(address).append(subjects).append(grade).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof StudentDto)) {
			return false;
		}
		StudentDto rhs = (StudentDto) other;
		return new EqualsBuilder().append(id, rhs.id).append(firstName, rhs.firstName).append(lastName, rhs.lastName)
				.append(birthdate, rhs.birthdate).append(phoneNumber, rhs.phoneNumber).append(email, rhs.email)
				.append(address, rhs.address).append(subjects, rhs.subjects).append(grade, rhs.grade).isEquals();
	}

}
