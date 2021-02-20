package com.abc.enrollment.adapter.datastore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.enrollment.EnrollmentApiApplication;
import com.abc.enrollment.config.JpaAuditingConfiguration;
import com.abc.enrollment.domain.Course;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EnrollmentApiApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({ CourseEntityRepository.class, JpaAuditingConfiguration.class })

public class CoursetEntityRepositoryTest {

	@Autowired
	private CourseEntityRepository subject;

	@Test
	public void should_create_student_and_retrieve_student() {
		Course course = Course.builder().classId("c1").className("Algorithms").credits(11).build();		
		subject.save(course);
		Course actual = subject.findByClassId("c1").get();
		assertEquals(course.getClassId(), actual.getClassId());
		assertEquals(course.getClassName(), actual.getClassName());
		assertEquals(course.getCredits(), actual.getCredits());
	}

}
