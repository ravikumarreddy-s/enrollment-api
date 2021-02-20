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
import com.abc.enrollment.domain.Student;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EnrollmentApiApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({ StudentEntityRepository.class, JpaAuditingConfiguration.class })

public class StudentEntityRepositoryTest {

	@Autowired
	private StudentEntityRepository subject;

	@Test
	public void should_create_student_and_retrieve_student() {
		Student student = Student.builder().firstName("fname").lastName("lname").studentId("100").build();
		subject.save(student);
		Student actual = subject.findByStudentId("100").get();
		assertEquals(student.getStudentId(), actual.getStudentId());
		assertEquals(student.getFirstName(), actual.getFirstName());
		assertEquals(student.getLastName(), actual.getLastName());
	}

}
