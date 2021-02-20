package com.abc.enrollment.adapter.datastore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.enrollment.domain.Course;
import com.abc.enrollment.domain.Student;

public class StudentEntityTransformerTest {

	private StudentEntityTransformer subject = new StudentEntityTransformer() {
	};

	@Test
	public void toStudent() {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setStudentId("S1");
		studentEntity.setFirstName("fname");
		studentEntity.setLastName("lastname");
		Student student = subject.toStudent().apply(studentEntity);
		assertEquals(student.getStudentId(), studentEntity.getStudentId());
		assertEquals(student.getFirstName(), studentEntity.getFirstName());
		assertEquals(student.getLastName(), studentEntity.getLastName());

	}

	@Test
	public void fromStudent() {
		Student student = Student.builder().studentId("S1").firstName("fname").lastName("lastname").build();
		StudentEntity studentEntity = subject.fromStudent().apply(student);
		assertEquals(studentEntity.getStudentId(), student.getStudentId());
		assertEquals(studentEntity.getFirstName(), student.getFirstName());
		assertEquals(studentEntity.getLastName(), student.getLastName());

	}

}
