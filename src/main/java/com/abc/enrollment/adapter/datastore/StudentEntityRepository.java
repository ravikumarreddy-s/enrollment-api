package com.abc.enrollment.adapter.datastore;

import org.springframework.stereotype.Component;

import com.abc.enrollment.domain.Student;
import com.abc.enrollment.domain.StudentRepository;

@Component
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
	public Student update(Student student) {
		return repository.save(student.transform(fromStudent())).transform(toStudent());

	}

}
