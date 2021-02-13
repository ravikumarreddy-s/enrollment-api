package com.abc.enrollment.adapter.datastore;

import java.util.function.Function;

import com.abc.enrollment.domain.Course;

public interface CourseEntityTransformer {

	default Function<CourseEntity, Course> toCourse() {
		return entity -> {
			return Course.builder().classId(entity.getClassId()).className(entity.getClassName())
					.credits(entity.getCredits()).id(entity.getId()).build();

		};
	}

	default Function<Course, CourseEntity> fromCourse() {
		return course -> {
			return CourseEntity.builder().classId(course.getClassId()).className(course.getClassName())
					.credits(course.getCredits()).id(course.getId()).build();

		};
	}

}