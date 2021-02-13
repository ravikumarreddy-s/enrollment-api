package com.abc.enrollment.adapter.datastore;

import java.util.function.Function;

import com.abc.enrollment.domain.Semester;

public interface SemesterEntityTransformer {

	default Function<SemesterEntity, Semester> toSemester() {
		return entity -> {
			return Semester.builder().semisterId(entity.getSemesterId()).semesterName(entity.getSemesterName())
					.id(entity.getId()).build();

		};
	}

	default Function<Semester, SemesterEntity> fromSemester() {
		return semester -> {
			return SemesterEntity.builder().semesterId(semester.getSemisterId())
					.semesterName(semester.getSemesterName()).id(semester.getId()).build();

		};
	}

}