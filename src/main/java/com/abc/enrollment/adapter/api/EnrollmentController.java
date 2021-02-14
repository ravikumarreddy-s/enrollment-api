/**
 * 
 */
package com.abc.enrollment.adapter.api;

import static org.springframework.http.ResponseEntity.ok;

import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.enrollment.adapter.apiclient.model.CourseRequest;
import com.abc.enrollment.adapter.apiclient.model.CourseResponse;
import com.abc.enrollment.adapter.apiclient.model.Courses;
import com.abc.enrollment.adapter.apiclient.model.EnrollmentRequest;
import com.abc.enrollment.adapter.apiclient.model.SemesterRequest;
import com.abc.enrollment.adapter.apiclient.model.StudentRequest;
import com.abc.enrollment.adapter.apiclient.model.StudentResponse;
import com.abc.enrollment.adapter.apiclient.model.Students;
import com.abc.enrollment.domain.Course;
import com.abc.enrollment.domain.CourseRepository;
import com.abc.enrollment.domain.Enrollment;
import com.abc.enrollment.domain.EnrollmentRepository;
import com.abc.enrollment.domain.Semester;
import com.abc.enrollment.domain.SemesterRepository;
import com.abc.enrollment.domain.Student;
import com.abc.enrollment.domain.StudentRepository;

/**
 * @author ravi
 *
 */
@RestController
public class EnrollmentController {

	private final StudentRepository studentRepository;

	private final SemesterRepository semesterRepository;

	private final CourseRepository courseRepository;

	private final EnrollmentRepository enrollmentRepository;

	public EnrollmentController(StudentRepository studentRepository, SemesterRepository semesterRepository,
			CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
		this.studentRepository = studentRepository;
		this.semesterRepository = semesterRepository;
		this.courseRepository = courseRepository;
		this.enrollmentRepository = enrollmentRepository;

	}

	@GetMapping("/health")
	public ResponseEntity<String> health() {
		System.out.println("Inside health");
		return ok().build();
	}

	@PostMapping(value = "/student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createStudent(@RequestBody StudentRequest studentRequest) {
		return ResponseEntity.ok(studentRepository.save(Student.builder().firstName(studentRequest.getFirstName())
				.lastName(studentRequest.getLastName()).studentId(studentRequest.getStudentId()).build())
				.getStudentId());
	}

	@PutMapping(value = "/student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateStudent(@RequestBody StudentRequest studentRequest) {
		return ResponseEntity.ok(studentRepository
				.save(Student.builder().firstName(studentRequest.getFirstName()).lastName(studentRequest.getLastName())
						.studentId(studentRequest.getStudentId())
						.id(studentRepository.findByStudentId(studentRequest.getStudentId()).get().getId()).build())
				.getStudentId());
	}

	@PostMapping(value = "/semester", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createSemester(@RequestBody SemesterRequest semesterRequest) {
		return ResponseEntity
				.ok(semesterRepository.save(Semester.builder().semesterName(semesterRequest.getSemesterName())
						.semisterId(semesterRequest.getSemesterId()).build()).getSemisterId());
	}

	@PutMapping(value = "/semester", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateSemester(@RequestBody SemesterRequest semesterRequest) {
		return ResponseEntity.ok(semesterRepository
				.save(Semester.builder().semesterName(semesterRequest.getSemesterName())
						.semisterId(semesterRequest.getSemesterId())
						.id(semesterRepository.findBySemesterId(semesterRequest.getSemesterId()).get().getId()).build())
				.getSemisterId());
	}

	@PostMapping(value = "/course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createCourse(@RequestBody CourseRequest courseRequest) {
		return ResponseEntity
				.ok(courseRepository
						.save(Course.builder().classId(courseRequest.getClassId())
								.className(courseRequest.getClassName()).credits(courseRequest.getCredits()).build())
						.getClassId());
	}

	@PutMapping(value = "/course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCourse(@RequestBody CourseRequest courseRequest) {
		return ResponseEntity.ok(courseRepository
				.save(Course.builder().classId(courseRequest.getClassId()).className(courseRequest.getClassName())
						.id(courseRepository.findByClassId(courseRequest.getClassId()).get().getId())
						.credits(courseRequest.getCredits()).build())
				.getClassId());
	}

	@PostMapping(value = "/enroll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> enroll(@RequestBody EnrollmentRequest enrollmentRequest) {

		enrollmentRequest.getEnrollments().forEach(enrollment -> {
			enrollmentRepository.save(Enrollment.builder().classId(enrollment.getClassId())
					.semesterId(enrollment.getSemesterId()).studentId(enrollmentRequest.getStudentId()).build());

		});

		return ResponseEntity.ok("Enrolled");
	}

	@GetMapping(value = "/fetch-students", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> getStudentsBySemester(@RequestParam String semId) {
		return ResponseEntity.ok(StudentResponse.builder()
				.students(enrollmentRepository.findAllStudentsEnrolledInAClassForSemester(semId).stream()
						.map(e -> Students.builder().studentId(e[0].toString()).studentName(e[1].toString()).build())
						.collect(Collectors.toList()))
				.build());
	}

	@GetMapping(value = "/fetch-courses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourseResponse> getCoursesBtSemesterAndStudent(@RequestParam String semId,
			@RequestParam String studentId) {
		return ResponseEntity.ok(CourseResponse.builder()
				.courses(enrollmentRepository.findAllClassesForAStudentForSemester(studentId, semId).stream()
						.map(e -> Courses.builder().courseId(e[0].toString()).courseName(e[1].toString()).build())
						.collect(Collectors.toList()))
				.build());
	}
}
