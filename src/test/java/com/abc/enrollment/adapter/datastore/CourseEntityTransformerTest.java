package com.abc.enrollment.adapter.datastore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.enrollment.domain.Course;

public class CourseEntityTransformerTest {

	private CourseEntityTransformer subject = new CourseEntityTransformer() {
	};

	@Test
	public void toCourse() {
		CourseEntity couseEntity = new CourseEntity();
		couseEntity.setClassId("C1");
		couseEntity.setClassName("Cname");
		couseEntity.setCredits(10);
		Course course = subject.toCourse().apply(couseEntity);
		assertEquals(course.getClassId(), couseEntity.getClassId());
		assertEquals(course.getClassName(), couseEntity.getClassName());
		assertEquals(course.getCredits(), couseEntity.getCredits());

	}

	@Test
	public void fromCourse() {
		Course course = Course.builder().classId("C1").className("Cname").credits(10).build();
		CourseEntity couseEntity = subject.fromCourse().apply(course);
		assertEquals(couseEntity.getClassId(), course.getClassId());
		assertEquals(couseEntity.getClassName(), course.getClassName());
		assertEquals(couseEntity.getCredits(), course.getCredits());

	}

}
