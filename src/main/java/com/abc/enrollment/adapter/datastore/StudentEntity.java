package com.abc.enrollment.adapter.datastore;

import java.time.Instant;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.abc.enrollment.domain.Transformer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STUDENT")
@Setter
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class StudentEntity implements Transformer<StudentEntity> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "student_id")
	private String studentId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
    @CreatedDate
	@Column(name = "created_date",updatable = false)
	private Instant createdDate;

    @LastModifiedBy
	@Column(name = "last_updated_by")
	private String lastUpdatedBy;

    @LastModifiedDate
	@Column(name = "last_updated_date")
	private Instant lastUpdatedDate;

	@Override
	public <R> R transform(Function<StudentEntity, R> f) {
		return f.apply(this);

	}
}
