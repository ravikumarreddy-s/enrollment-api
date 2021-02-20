package com.abc.enrollment.adapter.datastore;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStudentRepository extends JpaRepository<StudentEntity, Long> {
	
	  Optional<StudentEntity> findByStudentId(String studentId);


}
