package com.example.demo.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table(name = "LIBRARY")
@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(exclude = "books")
public class Library {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@OneToMany(mappedBy = "library", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Book> books;

}
