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
import com.abc.enrollment.adapter.apiclient.model.SemesterRequest;
import com.abc.enrollment.config.JpaAuditingConfiguration;
import com.abc.enrollment.domain.Course;
import com.abc.enrollment.domain.Semester;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EnrollmentApiApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({ SemesterEntityRepository.class, JpaAuditingConfiguration.class })

public class SemesterEntityRepositoryTest {

	@Autowired
	private SemesterEntityRepository subject;

	@Test
	public void should_create_student_and_retrieve_student() {
		Semester semester = Semester.builder().semisterId("S1").semesterName("Sem1").build();	
		subject.save(semester);
		Semester actual = subject.findBySemesterId("S1").get();
		assertEquals(semester.getSemisterId(), actual.getSemisterId());
		assertEquals(semester.getSemesterName(), actual.getSemesterName());
	}

}
