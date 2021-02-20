package com.abc.enrollment.adapter.apiclient.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class StudentRequest {
	
	@NotEmpty
	private String studentId;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

}
