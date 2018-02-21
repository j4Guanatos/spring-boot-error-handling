package org.j4guanatos.spring.boot.endpoint;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.j4guanatos.spring.boot.dto.StudentDto;
import org.j4guanatos.spring.boot.error.ErrorDto;
import org.j4guanatos.spring.boot.mapper.Mapper;
import org.j4guanatos.spring.boot.model.Student;
import org.j4guanatos.spring.boot.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/student2")
public class StudentEndpoint2 {

	private static final Logger logger = LoggerFactory.getLogger(StudentEndpoint2.class);

	@Autowired
	private Mapper<StudentDto, Student> studentMapper;

	@Autowired
	private StudentRepository studentRepository;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public StudentDto createStudent(@Valid @RequestBody StudentDto studentDto) {
		Student student = studentMapper.toModel(studentDto);
		student = studentRepository.insert(student);
		logger.debug("student: {}", student);
		return studentDto;
	}

	@GetMapping(value = "/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public StudentDto retrieveStudent(@PathVariable Long studentId) {
		return studentMapper.toDto(
				studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId)));
	}

	@PutMapping(value = "/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public StudentDto updateStudent(@Valid @RequestBody StudentDto studentDto, @PathVariable Long studentId) {
		Student student = studentMapper.toModel(studentDto);
		if (!studentRepository.existsById(studentId)) {
			logger.info("Student with id:{} not found", studentId);
			throw new StudentNotFoundException(studentId);
		}
		student.setId(studentId);
		studentRepository.save(student);

		return studentMapper.toDto(student);
	}

	@DeleteMapping(value = "/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Map<String, Boolean> deleteStudent(@PathVariable Long studentId) {
		if (!studentRepository.existsById(studentId)) {
			logger.info("Student with id:{} not found", studentId);
			throw new StudentNotFoundException(studentId);
		}
		studentRepository.deleteById(studentId);
		return Collections.singletonMap("success", true);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DuplicateKeyException.class)
	public ErrorDto duplicatedIdError(DuplicateKeyException ex) {
		logger.info("Id already exists");
		return new ErrorDto(HttpStatus.BAD_REQUEST, "Id already exists", "/student2", UUID.randomUUID().toString());
	}

}

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student not found") // 404
class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long id;

	/**
	 * Exception thrown internally whenever a student is not found in the
	 * database by id.
	 *
	 * @param id
	 *            id of the student
	 */
	public StudentNotFoundException(Long id) {
		super(String.format("Student with id: %s not found in the database", id));
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
