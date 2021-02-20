package com.abc.enrollment.adapter.datastore;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.abc.enrollment.domain.Student;
import com.abc.enrollment.domain.StudentRepository;

@Repository
public class StudentEntityRepository implements StudentRepository, StudentEntityTransformer {

	private final JpaStudentRepository repository;

	public StudentEntityRepository(JpaStudentRepository repository) {
		this.repository = repository;
	}

	@Override
	public Student save(Student student) {
		return repository.save(student.transform(fromStudent())).transform(toStudent());
	}

	@Override
	public Optional<Student> findByStudentId(String studentId) {
		return repository.findByStudentId(studentId).map(toStudent());
	}

}
