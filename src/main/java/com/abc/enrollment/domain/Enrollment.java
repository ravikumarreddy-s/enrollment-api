package com.abc.enrollment.domain;

import java.util.function.Function;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Enrollment implements Transformer<Enrollment> {

	private Long id;

	private String classId;
	
	private String studentId;
	
	private String semesterId;

	@Override
	public <R> R transform(Function<Enrollment, R> f) {
		return f.apply(this);
	}

}
