package com.abc.enrollment.adapter.apiclient.model;

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
public class EnrollRequest {
	
	@NotEmpty
	private String classId;
		
	@NotEmpty
	private String semesterId;


}
