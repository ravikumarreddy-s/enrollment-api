/**
 * 
 */
package com.abc.enrollment.adapter.api;

import static org.springframework.http.ResponseEntity.ok;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.abc.enrollment.adapter.apiclient.model.EnrollmentResponse;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author ravi
 *
 */
@RestController
@Validated
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

	@ApiOperation(notes = "This endpoint add new students", value = "Creates Student")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Student record created successfully", response = EnrollmentResponse.class),
			@ApiResponse(code = 400, message = "One or Input values are invalid"),
			@ApiResponse(code = 500, message = "Internal server Error") })
	@PostMapping(value = "/student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnrollmentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
		return ResponseEntity.ok(EnrollmentResponse.builder().code("200")
				.message(studentRepository.save(Student.builder().firstName(studentRequest.getFirstName())
						.lastName(studentRequest.getLastName()).studentId(studentRequest.getStudentId()).build())
						.getStudentId() + " created successfully")
				.build());
	}

	@ApiOperation(notes = "This endpoint updates students", value = "Updates Student")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Student record updated successfully", response = EnrollmentResponse.class),
			@ApiResponse(code = 400, message = "One or Input values are invalid"),
			@ApiResponse(code = 500, message = "Internal server Error") })
	@PutMapping(value = "/student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnrollmentResponse> updateStudent(@Valid @RequestBody StudentRequest studentRequest) {
		return ResponseEntity
				.ok(EnrollmentResponse.builder().code("200")
						.message(
								studentRepository
										.save(Student.builder().firstName(studentRequest.getFirstName())
												.lastName(studentRequest.getLastName())
												.studentId(studentRequest.getStudentId())
												.id(studentRepository.findByStudentId(studentRequest.getStudentId())
														.get().getId())
												.build())
										.getStudentId() + " updated successfully")
						.build());
	}

	@ApiOperation(notes = "This endpoint add new semester", value = "Creates semester")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Semester record created successfully", response = EnrollmentResponse.class),
			@ApiResponse(code = 400, message = "One or Input values are invalid"),
			@ApiResponse(code = 500, message = "Internal server Error") })
	@PostMapping(value = "/semester", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnrollmentResponse> createSemester(@RequestBody SemesterRequest semesterRequest) {

		return ResponseEntity.ok(EnrollmentResponse.builder().code("200")
				.message(semesterRepository
						.save(Semester.builder().semesterName(semesterRequest.getSemesterName())
								.semisterId(semesterRequest.getSemesterId()).build())
						.getSemisterId() + " created successfully")
				.build());
	}

	@ApiOperation(notes = "This endpoint updates semester", value = "Updates semester")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Semester record updated successfully", response = EnrollmentResponse.class),
			@ApiResponse(code = 400, message = "One or Input values are invalid"),
			@ApiResponse(code = 500, message = "Internal server Error") })
	@PutMapping(value = "/semester", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnrollmentResponse> updateSemester(@RequestBody SemesterRequest semesterRequest) {
		return ResponseEntity
				.ok(EnrollmentResponse.builder().code("200")
						.message(
								semesterRepository
										.save(Semester.builder().semesterName(semesterRequest.getSemesterName())
												.semisterId(semesterRequest.getSemesterId())
												.id(semesterRepository.findBySemesterId(semesterRequest.getSemesterId())
														.get().getId())
												.build())
										.getSemisterId() + " updated successfully")
						.build());
	}

	@ApiOperation(notes = "This endpoint add new courses", value = "Creates semester")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Semester record Updated successfully", response = EnrollmentResponse.class),
			@ApiResponse(code = 400, message = "One or Input values are invalid"),
			@ApiResponse(code = 500, message = "Internal server Error") })
	@PostMapping(value = "/course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnrollmentResponse> createCourse(@RequestBody CourseRequest courseRequest) {
		return ResponseEntity
				.ok(EnrollmentResponse.builder().code("200")
						.message(courseRepository.save(Course.builder().classId(courseRequest.getClassId())
								.className(courseRequest.getClassName()).credits(courseRequest.getCredits()).build())
								.getClassId() + " created successfully")
						.build());
	}

	@ApiOperation(notes = "This endpoint update courses", value = "Updates courses")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Courses record created successfully", response = EnrollmentResponse.class),
			@ApiResponse(code = 400, message = "One or Input values are invalid"),
			@ApiResponse(code = 500, message = "Internal server Error") })
	@PutMapping(value = "/course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnrollmentResponse> updateCourse(@RequestBody CourseRequest courseRequest) {
		return ResponseEntity
				.ok(EnrollmentResponse.builder().code("200")
						.message(
								courseRepository
										.save(Course.builder().classId(courseRequest.getClassId())
												.className(courseRequest.getClassName())
												.id(courseRepository.findByClassId(courseRequest.getClassId()).get()
														.getId())
												.credits(courseRequest.getCredits()).build())
										.getClassId() + " updated successfully")
						.build());
	}

	@ApiOperation(notes = "This endpoint enrolls students for courses for a semester", value = "Enroll students for courses for semester")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Enrollmenet completed successfully", response = EnrollmentResponse.class),
			@ApiResponse(code = 400, message = "One or Input values are invalid"),
			@ApiResponse(code = 500, message = "Internal server Error") })
	@PostMapping(value = "/enroll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnrollmentResponse> enroll(@RequestBody EnrollmentRequest enrollmentRequest) {

		int credits = enrollmentRequest.getEnrollments().stream()
				.mapToInt(c -> courseRepository.findByClassId(c.getClassId()).get().getCredits()).sum();
//		int oldCredits= enrollmentRepository.findCreditsForStudentForSemester(enrollmentRequest, null)
		if (credits < 10) {
			return ResponseEntity.badRequest().body(EnrollmentResponse.builder().code("201")
					.message("Minimum 10 Credits Required to be considered full time").build());
		} else if (credits > 20) {
			return ResponseEntity.badRequest().body(EnrollmentResponse.builder().code("201")
					.message("Maximim 20 Credits per Semester allowed").build());
		}

		enrollmentRequest.getEnrollments().forEach(enrollment -> {
			enrollmentRepository.save(Enrollment.builder().classId(enrollment.getClassId())
					.semesterId(enrollment.getSemesterId()).studentId(enrollmentRequest.getStudentId()).build());

		});

		return ResponseEntity.ok(EnrollmentResponse.builder().code("200").message("Enrollment Completed").build());
	}

	@ApiOperation(notes = "This endpoint fetches students by semester id", value = "Retrieves students by semester id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Students records retrieved successfully", response = StudentResponse.class),
			@ApiResponse(code = 400, message = "One or Input values are invalid"),
			@ApiResponse(code = 500, message = "Internal server Error") })
	@GetMapping(value = "/fetch-students", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> getStudentsBySemester(@RequestParam String semId) {
		return ResponseEntity.ok(StudentResponse.builder()
				.students(enrollmentRepository.findAllStudentsEnrolledInAClassForSemester(semId).stream()
						.map(e -> Students.builder().studentId(e[0].toString()).studentName(e[1].toString()).build())
						.collect(Collectors.toList()))
				.build());
	}

	@ApiOperation(notes = "This endpoint fetches courses by semester id and student id", value = "Retrieves courses by semester id and student id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Courses retrieved successfully", response = CourseResponse.class),
			@ApiResponse(code = 400, message = "One or Input values are invalid"),
			@ApiResponse(code = 500, message = "Internal server Error") })
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
