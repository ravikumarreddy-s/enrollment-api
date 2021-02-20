package com.abc.enrollment.adapter.datastore;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSemesterRepository extends JpaRepository<SemesterEntity, Long> {
	
	  Optional<SemesterEntity> findBySemesterId(String semesterId);


}
