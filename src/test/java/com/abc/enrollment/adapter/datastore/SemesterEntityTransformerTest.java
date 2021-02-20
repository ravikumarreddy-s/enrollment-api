package com.abc.enrollment.adapter.datastore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.enrollment.domain.Semester;
import com.abc.enrollment.domain.Student;

public class SemesterEntityTransformerTest {

	private SemesterEntityTransformer subject = new SemesterEntityTransformer() {
	};

	@Test
	public void toSemester() {
		SemesterEntity semesterEntity = new SemesterEntity();
		semesterEntity.setSemesterId("S1");
		semesterEntity.setSemesterName("SEM1");
		Semester semester = subject.toSemester().apply(semesterEntity);
		assertEquals(semester.getSemisterId(), semesterEntity.getSemesterId());
		assertEquals(semester.getSemesterName(), semesterEntity.getSemesterName());
	}

	@Test
	public void fromSemester() {
		Semester semester = Semester.builder().semisterId("S1").semesterName("SEM1").build();
		SemesterEntity semesterEntity = subject.fromSemester().apply(semester);
		assertEquals(semesterEntity.getSemesterId(), semester.getSemisterId());
		assertEquals(semesterEntity.getSemesterName(), semester.getSemesterName());
	}

}
