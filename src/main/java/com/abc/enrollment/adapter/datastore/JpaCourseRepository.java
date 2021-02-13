package com.abc.enrollment.adapter.datastore;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCourseRepository extends JpaRepository<CourseEntity, Long> {
	
	  Optional<CourseEntity> findByClassId(String classId);


}
