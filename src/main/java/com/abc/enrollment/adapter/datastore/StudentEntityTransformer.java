package com.abc.enrollment.adapter.datastore;

import java.util.function.Function;

import com.abc.enrollment.domain.Student;

public interface StudentEntityTransformer {

	default Function<StudentEntity, Student> toStudent() {
		return entity -> {
			return Student.builder().firstName(entity.getFirstName()).lastName(entity.getLastName())
					.studentId(entity.getStudentId()).id(entity.getId()).build();

		};
	}

	default Function<Student, StudentEntity> fromStudent() {
		return student -> {
			return StudentEntity.builder().firstName(student.getFirstName()).lastName(student.getLastName())
					.studentId(student.getStudentId()).id(student.getId()).build();

		};
	}

}