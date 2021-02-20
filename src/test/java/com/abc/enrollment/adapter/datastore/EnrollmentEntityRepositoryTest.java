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
import com.abc.enrollment.domain.Enrollment;
import com.abc.enrollment.domain.Semester;
import com.abc.enrollment.domain.Student;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EnrollmentApiApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({ EnrollmentEntityRepository.class, StudentEntityRepository.class, SemesterEntityRepository.class,
		CourseEntityRepository.class, JpaAuditingConfiguration.class })

public class EnrollmentEntityRepositoryTest {

	@Autowired
	private EnrollmentEntityRepository enrollmentEntityRepository;

	@Autowired
	private CourseEntityRepository courseEntityRepository;

	@Autowired
	private StudentEntityRepository studentEntityRepository;

	@Autowired
	private SemesterEntityRepository semesterEntityRepository;

	@Test
	public void should_create_enrollment_and_retrieve_enrollment() {
		Course course = Course.builder().classId("c1").className("Algorithms").credits(11).build();
		courseEntityRepository.save(course);
		Student student = Student.builder().firstName("fname").lastName("lname").studentId("526").build();
		studentEntityRepository.save(student);
		Semester semester = Semester.builder().semisterId("s1").semesterName("Sem1").build();
		semesterEntityRepository.save(semester);
		Enrollment enrollment = Enrollment.builder().classId("c1").semesterId("s1").studentId("526").build();
		enrollmentEntityRepository.save(enrollment);
		assertEquals(course.getCredits(), enrollmentEntityRepository.findCreditsForStudentForSemester("526", "s1"));

		Object[] studentsArray = enrollmentEntityRepository.findAllStudentsEnrolledInAClassForSemester("s1").stream()
				.findFirst().get();
		assertEquals(student.getStudentId(), studentsArray[0]);
		assertEquals(student.getFirstName(), studentsArray[1]);

		Object[] courseArray = enrollmentEntityRepository.findAllClassesForAStudentForSemester("526", "s1").stream()
				.findFirst().get();
		assertEquals(course.getClassId(), courseArray[0]);
		assertEquals(course.getClassName(), courseArray[1]);

	}

}
