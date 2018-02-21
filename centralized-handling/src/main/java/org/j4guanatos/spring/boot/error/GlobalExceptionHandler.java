package org.j4guanatos.spring.boot.error;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * A single place to customize the response body of all Exception types.
	 * <p>
	 * The default implementation sets the
	 * {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE} request attribute and creates
	 * a {@link ResponseEntity} from the given body, headers, and status.
	 *
	 * @param ex
	 *            the exception
	 * @param body
	 *            the body for the response
	 * @param headers
	 *            the headers for the response
	 * @param status
	 *            the response status
	 * @param request
	 *            the current request
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDto errorDto = (ErrorDto) body;
		HttpStatus redefinedStatus = status;

		if (errorDto == null) {
			errorDto = createErrorDto(status, ex.getMessage(), request);
		}
		log.info("Error found while processing the request. Error id: {}. Error: {}", errorDto.getId(), ex);
		return new ResponseEntity<>(errorDto, headers, redefinedStatus);
	}

	/**
	 * Default exception handling. It will rely on
	 * {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}
	 * to do its job and it will return an internal server error status
	 *
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	protected ResponseEntity<Object> handleDefaultException(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorDto errorDto = createErrorDto(status, ex.getMessage(), request);
		return handleExceptionInternal(ex, errorDto, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(value = DuplicatedDbValue.class)
	protected ResponseEntity<Object> handleDuplicatedDbValue(DuplicatedDbValue ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorDto errorDto = createErrorDto(status, ex.getMessage(), request);
		return handleExceptionInternal(ex, errorDto, new HttpHeaders(), status, request);
	}

	/**
	 *
	 * @param status
	 *            HTTP status for the error
	 * @param message
	 *            The message retrieved from properties file using messageSource
	 * @param request
	 *            Web request (wrapping a
	 *            {@link javax.servlet.http.HttpServletRequest}) to get extra
	 *            information
	 * @return return a fresh errorDto to be sent to the user ErrorDto
	 */
	private static ErrorDto createErrorDto(HttpStatus status, String message, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURI();
		String guid = UUID.randomUUID().toString();
		return new ErrorDto(status, message, path, guid);
	}

}
