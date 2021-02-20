package com.abc.enrollment.adapter.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.abc.enrollment.adapter.apiclient.model.CourseRequest;
import com.abc.enrollment.adapter.apiclient.model.EnrollRequest;
import com.abc.enrollment.adapter.apiclient.model.EnrollmentRequest;
import com.abc.enrollment.adapter.apiclient.model.EnrollmentResponse;
import com.abc.enrollment.adapter.apiclient.model.SemesterRequest;
import com.abc.enrollment.adapter.apiclient.model.StudentRequest;
import com.abc.enrollment.config.EnrollmentExceptionHandler;
import com.abc.enrollment.domain.Course;
import com.abc.enrollment.domain.CourseRepository;
import com.abc.enrollment.domain.Enrollment;
import com.abc.enrollment.domain.EnrollmentRepository;
import com.abc.enrollment.domain.Semester;
import com.abc.enrollment.domain.SemesterRepository;
import com.abc.enrollment.domain.Student;
import com.abc.enrollment.domain.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EnrollmentControllerTest {

	@Autowired
	EnrollmentController enrollmentController;

	private StudentRequest studentRequest;

	private SemesterRequest semesterRequest;

	private CourseRequest courseRequest;

	private EnrollmentRequest enrollmentRequest;

	private MockMvc mockMVC;

	@MockBean
	private StudentRepository studentRepository;
	@MockBean
	private SemesterRepository semesterRepository;
	@MockBean
	private CourseRepository courseRepository;
	@MockBean
	private EnrollmentRepository enrollmentRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMVC = MockMvcBuilders.standaloneSetup(enrollmentController)
				.setControllerAdvice(new EnrollmentExceptionHandler()).build();
		studentRequest = StudentRequest.builder().firstName("fname").lastName("lname").studentId("100").build();
		semesterRequest = SemesterRequest.builder().semesterId("S1").semesterName("Sem1").build();
		courseRequest = CourseRequest.builder().classId("c1").className("Algorithms").credits(11).build();
		List<EnrollRequest> enrollReqList = new ArrayList<EnrollRequest>();
		enrollReqList.add(EnrollRequest.builder().classId("c1").semesterId("s1").build());
		enrollmentRequest = EnrollmentRequest.builder().enrollments(enrollReqList).studentId("s1").build();
	}

	@Test
	public void createStudent() throws Exception {
		when(studentRepository.save(any(Student.class))).thenReturn(Student.builder().build());
		MvcResult mvcResult = mockMVC.perform(post("/student").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(studentRequest))).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void updateStudent() throws Exception {
		when(studentRepository.save(any(Student.class))).thenReturn(Student.builder().build());
		when(studentRepository.findByStudentId(any(String.class))).thenReturn(Optional.of(Student.builder().build()));

		MvcResult mvcResult = mockMVC.perform(put("/student").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(studentRequest))).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void createSemester() throws Exception {
		when(semesterRepository.save(any(Semester.class))).thenReturn(Semester.builder().build());
		MvcResult mvcResult = mockMVC.perform(post("/semester").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(semesterRequest))).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void updateSemester() throws Exception {
		when(semesterRepository.save(any(Semester.class))).thenReturn(Semester.builder().build());
		when(semesterRepository.findBySemesterId(any(String.class)))
				.thenReturn(Optional.of(Semester.builder().build()));

		MvcResult mvcResult = mockMVC.perform(put("/semester").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(semesterRequest))).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void createCourse() throws Exception {
		when(courseRepository.save(any(Course.class))).thenReturn(Course.builder().build());
		MvcResult mvcResult = mockMVC.perform(post("/course").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(courseRequest))).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void updateCourse() throws Exception {
		when(courseRepository.save(any(Course.class))).thenReturn(Course.builder().build());
		when(courseRepository.findByClassId(any(String.class))).thenReturn(Optional.of(Course.builder().build()));

		MvcResult mvcResult = mockMVC.perform(put("/course").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(courseRequest))).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void enroll() throws Exception {
		when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(Enrollment.builder().build());
		when(courseRepository.findByClassId(any(String.class))).thenReturn(Optional.of(Course.builder().classId("C1").credits(11).build()));
		MvcResult mvcResult = mockMVC.perform(post("/enroll").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(enrollmentRequest))).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
