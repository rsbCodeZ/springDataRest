package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

@Table(name = "BOOK")
@Entity
@Data
@EqualsAndHashCode(exclude = "library")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue
	private Integer id;

	private String title;

	private String author;

	private Integer stockCount;

	@ManyToOne
	@JoinColumn(name = "library_id", referencedColumnName = "id", nullable = false, updatable = false)
	@JsonIgnore
	@RestResource(exported = false)
	private Library library;

}
