package com.abc.enrollment.adapter.apiclient.model;

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
public class EnrollmentResponse {
	
	private String code;
	
	private String message;

}
