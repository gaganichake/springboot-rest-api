package com.examples.resource;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "pages") // Optional. Allow to customize table name.
public class Page  implements Serializable {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 3572737783746941813L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private final String chapter;
	private final String content;
	private final Integer pageNumber;

	// Fetch type is Eager so it proactively load book title when page object is created.
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	// This will create a foreign key book_id in pages table
	@JoinColumn(name = "book_id", nullable = false)
	private final Book book;

	public Page() {
        chapter = null;
        content = null;
        pageNumber = null;
		book = null;
    }

	public Page(String chapter, String content, Integer pageNumber, Book book) {
		super();
		this.chapter = chapter;
		this.content = content;
		this.pageNumber = pageNumber;
		this.book = book;
	}

	@Override
	public String toString() {
		return "Page{" +
				"id=" + id +
				", chapter='" + chapter + '\'' +
				", content='" + content + '\'' +
				", pageNumber=" + pageNumber +
				", book=" + book + // FetchType.LAZY will cause org.hibernate.LazyInitializationException.
				'}';
	}
}
