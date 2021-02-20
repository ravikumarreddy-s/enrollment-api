package com.abc.enrollment.adapter.apiclient.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentRequest {
	
	@NotEmpty
	private String studentId;
	
	@NotEmpty
	private List<EnrollRequest> enrollments;


}
