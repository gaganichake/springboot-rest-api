package com.examples.resource;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
@Table(name = "books") // Optional. Allow to customize table name.
public class Book  implements Serializable {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 7512453394834301104L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// Transient will make this field non-serializable (non-persistent)
	@Transient
	private final String author;
	
	private final String title;

	// Fetch type is Lazy so it does not load all pages when book object is created.
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Page> pages;

	public Book() {
		this.author = null;
		this.title = null;
	}

	public Book(String author, String title) {
		super();
		this.author = author;
		this.title = title;
	}

	// equals & hashCode methods
	// Override the default implementation provided by Java
	// Not required but better for hibernate to deal with complex operations in future
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;

		Book book = (Book) o;
		return Objects.equals(id, book.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", author='" + author + '\'' +
				", title='" + title + '\'' +
//				", pages=" + pages + // Print Pages will cause stack overflow exception due to cyclic dependency (recursion).
				'}';
	}
}
