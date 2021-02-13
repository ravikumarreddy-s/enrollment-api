package com.abc.enrollment.adapter.datastore;

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

}
