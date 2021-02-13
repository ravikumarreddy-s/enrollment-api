package com.abc.enrollment.adapter.datastore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStudentRepository extends JpaRepository<StudentEntity, Long> {

}
