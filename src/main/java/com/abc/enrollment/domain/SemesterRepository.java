package com.abc.enrollment.domain;

import java.util.Optional;

public interface SemesterRepository {

	Semester save(Semester semester);

	Optional<Semester> findBySemesterId(String semesterId);

}
