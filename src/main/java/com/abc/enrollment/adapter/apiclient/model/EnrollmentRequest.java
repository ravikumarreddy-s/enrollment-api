package com.abc.enrollment.adapter.apiclient.model;

import java.util.List;

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
	
	private String studentId;
	
	private List<EnrollRequest> enrollments;


}
