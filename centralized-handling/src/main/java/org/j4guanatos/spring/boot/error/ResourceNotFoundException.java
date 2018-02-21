package org.j4guanatos.spring.boot.error;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String resource;
	private final String fieldName;
	private final String fieldValue;

	/**
	 * Exception thrown internally whenever a resource is not found in the
	 * database.
	 *
	 * @param resource
	 *            name of the resource, e.g.: Student, Classroom, etc
	 * @param fieldName
	 *            name of the field used to look for the resource, e.g.: id,
	 *            name, class name
	 * @param fieldValue
	 *            value used to look for the resource. e.g.: 123, John, Math
	 */
	public ResourceNotFoundException(String resource, String fieldName, String fieldValue) {
		super(String.format("%s with %s : %s not found", resource, fieldName, fieldValue));
		this.resource = resource;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getResource() {
		return resource;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

}
