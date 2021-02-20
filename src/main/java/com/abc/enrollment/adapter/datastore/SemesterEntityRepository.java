package com.abc.enrollment.adapter.datastore;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.abc.enrollment.domain.Semester;
import com.abc.enrollment.domain.SemesterRepository;

@Repository
public class SemesterEntityRepository implements SemesterRepository, SemesterEntityTransformer {

	private final JpaSemesterRepository repository;

	public SemesterEntityRepository(JpaSemesterRepository repository) {
		this.repository = repository;
	}

	@Override
	public Semester save(Semester semester) {
		return repository.save(semester.transform(fromSemester())).transform(toSemester());
	}

	@Override
	public Optional<Semester> findBySemesterId(String semesterId) {
		return repository.findBySemesterId(semesterId).map(toSemester());
	}

}
