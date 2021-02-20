package com.abc.enrollment.domain;

import java.util.function.Function;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Student implements Transformer<Student> {

	private Long id;

	private String studentId;

	private String firstName;

	private String lastName;

	@Override
	public <R> R transform(Function<Student, R> f) {
		return f.apply(this);
	}

}
