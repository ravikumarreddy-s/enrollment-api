/**
 * 
 */
package com.abc.enrollment.adapter.api;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.enrollment.adapter.apiclient.model.StudentRequest;
import com.abc.enrollment.domain.Student;
import com.abc.enrollment.domain.StudentRepository;

/**
 * @author ravi
 *
 */
@RestController
public class EnrollmentController {

	private final StudentRepository studentRepository;

	public EnrollmentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
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
		return ResponseEntity.ok(studentRepository.save(Student.builder().firstName(studentRequest.getFirstName())
				.lastName(studentRequest.getLastName()).studentId(studentRequest.getStudentId()).build())
				.getStudentId());
	}

}
