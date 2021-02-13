package com.abc.enrollment.domain;

import java.util.function.Function;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Semester implements Transformer<Semester> {

	private Long id;

	private String semisterId;
	
	private String semesterName;

	@Override
	public <R> R transform(Function<Semester, R> f) {
		return f.apply(this);
	}

}
