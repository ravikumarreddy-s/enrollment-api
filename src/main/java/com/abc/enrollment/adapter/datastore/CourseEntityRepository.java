package com.abc.enrollment.adapter.datastore;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.abc.enrollment.domain.Course;
import com.abc.enrollment.domain.CourseRepository;

@Repository
public class CourseEntityRepository implements CourseRepository, CourseEntityTransformer {

	private final JpaCourseRepository repository;

	public CourseEntityRepository(JpaCourseRepository repository) {
		this.repository = repository;
	}

	@Override
	public Course save(Course course) {
		return repository.save(course.transform(fromCourse())).transform(toCourse());
	}

	@Override
	public Optional<Course> findByClassId(String classId) {
		return repository.findByClassId(classId).map(toCourse());
	}

}
