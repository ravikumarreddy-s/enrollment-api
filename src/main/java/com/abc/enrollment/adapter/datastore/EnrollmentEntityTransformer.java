package com.abc.enrollment.adapter.datastore;

import java.util.function.Function;

import com.abc.enrollment.domain.Enrollment;

public interface EnrollmentEntityTransformer {

	default Function<EnrollmentEntity, Enrollment> toEnrollment() {
		return entity -> {
			return Enrollment.builder().classId(entity.getClassId()).semesterId(entity.getSemesterId())
					.studentId(entity.getStudentId()).id(entity.getId()).build();

		};
	}

	default Function<Enrollment, EnrollmentEntity> fromEnrollment() {
		return enrollment -> {
			return EnrollmentEntity.builder().classId(enrollment.getClassId()).semesterId(enrollment.getSemesterId())
					.studentId(enrollment.getStudentId()).id(enrollment.getId()).build();

		};
	}

}