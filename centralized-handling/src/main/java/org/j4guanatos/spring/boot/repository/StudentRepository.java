package org.j4guanatos.spring.boot.repository;

import org.j4guanatos.spring.boot.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, Long> {

}
