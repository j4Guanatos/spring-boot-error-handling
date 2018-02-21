package org.j4guanatos.spring.boot.error;

public class DuplicatedDbValue extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String fieldName;
	private final String fieldValue;

	/**
	 * Exception thrown when a database constrains violation occurs
	 *
	 * @param fieldName
	 *            Name of the field, e.g.: id, name
	 * @param fieldValue
	 *            Value sent in the body, e.g.= 16, John
	 */
	public DuplicatedDbValue(String fieldName, String fieldValue) {
		super(String.format("Duplicated value: field %s with value %s already exists in database", fieldName,
				fieldValue));
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

}
