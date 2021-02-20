package com.abc.enrollment.adapter.datastore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.enrollment.domain.Course;
import com.abc.enrollment.domain.Enrollment;
import com.abc.enrollment.domain.Student;

public class EnrollmentEntityTransformerTest {

	private EnrollmentEntityTransformer subject = new EnrollmentEntityTransformer() {
	};

	@Test
	public void toEnrollment() {
		EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
		enrollmentEntity.setStudentId("S1");
		enrollmentEntity.setSemesterId("S1");
		enrollmentEntity.setClassId("C1");
		Enrollment enrollment = subject.toEnrollment().apply(enrollmentEntity);
		assertEquals(enrollment.getStudentId(), enrollmentEntity.getStudentId());
		assertEquals(enrollment.getSemesterId(), enrollmentEntity.getSemesterId());
		assertEquals(enrollment.getClassId(), enrollmentEntity.getClassId());

	}

	@Test
	public void fromEnrollment() {
		Enrollment enrollment = Enrollment.builder().studentId("S1").semesterId("S1").classId("C1").build();
		EnrollmentEntity enrollmentEntity = subject.fromEnrollment().apply(enrollment);
		assertEquals(enrollmentEntity.getStudentId(), enrollment.getStudentId());
		assertEquals(enrollmentEntity.getSemesterId(), enrollment.getSemesterId());
		assertEquals(enrollmentEntity.getClassId(), enrollment.getClassId());
	}

}
