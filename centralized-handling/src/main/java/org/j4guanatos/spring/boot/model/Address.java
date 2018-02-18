package org.j4guanatos.spring.boot.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**************************************************************************
 * Address.java spring-boot-sample-student project
 *
 * @since Nov 10, 2016
 **************************************************************************/
public class Address {

	private String street;
	private String number;
	private String postalCode;
	private String city;
	private String state;
	private String country;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(street).append(number).append(postalCode).append(city).append(state)
				.append(country).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof Address)) {
			return false;
		}
		Address rhs = (Address) other;
		return new EqualsBuilder().append(street, rhs.street).append(number, rhs.number)
				.append(postalCode, rhs.postalCode).append(city, rhs.city).append(state, rhs.state)
				.append(country, rhs.country).isEquals();
	}

}
