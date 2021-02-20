package com.abc.enrollment.domain;

import java.util.Collection;

public interface EnrollmentRepository {

	Enrollment save(Enrollment enrollment);
	
	Collection<Object[]> findAllStudentsEnrolledInAClassForSemester(String semesterId);
	
	Collection<Object[]> findAllClassesForAStudentForSemester(String studentId,String semesterId);

	Integer findCreditsForStudentForSemester(String studentId,String semesterId);

}
