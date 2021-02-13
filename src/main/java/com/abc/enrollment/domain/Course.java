package com.abc.enrollment.domain;

import java.util.function.Function;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Course implements Transformer<Course> {

	private Long id;

	private String classId;
	
	private String className;
	
	private Integer credits;

	@Override
	public <R> R transform(Function<Course, R> f) {
		return f.apply(this);
	}

}
