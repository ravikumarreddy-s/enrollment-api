package com.abc.enrollment.domain;

import java.util.Optional;

public interface StudentRepository {

	Student save(Student student);

	Optional<Student> findByStudentId(String studentId);

}
