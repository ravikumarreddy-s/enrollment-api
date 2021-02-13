package com.abc.enrollment.domain;

import java.util.Optional;

public interface CourseRepository {

	Course save(Course course);

	Optional<Course> findByClassId(String classId);

}
