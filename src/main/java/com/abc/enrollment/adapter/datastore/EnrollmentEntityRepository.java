package com.abc.enrollment.adapter.datastore;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.abc.enrollment.domain.Enrollment;
import com.abc.enrollment.domain.EnrollmentRepository;

@Component
public class EnrollmentEntityRepository implements EnrollmentRepository, EnrollmentEntityTransformer {

	private final JpaEnrollmentRepository repository;

	public EnrollmentEntityRepository(JpaEnrollmentRepository repository) {
		this.repository = repository;
	}

	@Override
	public Enrollment save(Enrollment enrollment) {
		return repository.save(enrollment.transform(fromEnrollment())).transform(toEnrollment());
	}

	@Override
	public Collection<Object[]> findAllStudentsEnrolledInAClassForSemester(String semesterId) {
		return repository.findAllStudentsEnrolledInAClassForSemester(semesterId);
	}

	@Override
	public Collection<Object[]> findAllClassesForAStudentForSemester(String studentId, String semesterId) {
		return repository.findAllClassesForAStudentForSemester(studentId, semesterId);
	}

}
