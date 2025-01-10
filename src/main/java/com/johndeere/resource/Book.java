package com.johndeere.resource;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "books") // Optional. Allow to customize table name.
public class Book  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7512453394834301104L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String author;
	
	private String title;
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Page> pages;


	public Book() {
		super();
	}

	public Book(String author, String title) {
		super();
		this.author = author;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public Set<Page> getPages() {
		return pages;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title + ", pages=" + pages + "]";
	}

	
}
