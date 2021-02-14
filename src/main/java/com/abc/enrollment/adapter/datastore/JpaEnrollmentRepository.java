package com.abc.enrollment.adapter.datastore;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaEnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

	@Query(value = "SELECT E.STUDENT_ID,S.FIRST_NAME FROM ENROLLMENT E,  STUDENT S , SEMESTER S2, CLASS C WHERE E.STUDENT_ID=S.STUDENT_ID and E.SEM_ID = S2.SEM_ID and E.CLASS_ID=C.CLASS_ID and E.SEM_ID=?1", nativeQuery = true)
	Collection<Object[]> findAllStudentsEnrolledInAClassForSemester(String semesterId);
	
	@Query(value = "SELECT C.CLASS_ID,C.CLASS_NAME FROM ENROLLMENT E,  STUDENT S , SEMESTER S2, CLASS C WHERE E.STUDENT_ID=S.STUDENT_ID and E.SEM_ID = S2.SEM_ID and E.CLASS_ID=C.CLASS_ID and E.STUDENT_ID=?1 AND E.SEM_ID=?2", nativeQuery = true)
	Collection<Object[]> findAllClassesForAStudentForSemester(String studentId,String semesterId);

}
