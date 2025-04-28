package com.examples.resource;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
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
	
	private final String author;
	
	private final String title;
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Page> pages;


	public Book(String author, String title) {
		super();
		this.author = author;
		this.title = title;
	}

    @Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title + ", pages=" + pages + "]";
	}

	
}
