package org.j4guanatos.spring.boot.error;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

public class ErrorDto {

	private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	private final String timestamp;
	private final Integer status;
	private final String message;
	private final String path;
	private final String id;

	public ErrorDto(HttpStatus status, String message, String path, String id) {
		super();
		this.timestamp = ZonedDateTime.now().format(DATE_TIME_FORMATTER);
		this.status = status.value();
		this.message = message;
		this.path = path;
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public String getId() {
		return id;
	}

}
