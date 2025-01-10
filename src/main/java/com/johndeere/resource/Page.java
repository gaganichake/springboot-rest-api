package com.johndeere.resource;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "pages") // Optional. Allow to customize table name.
public class Page  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3572737783746941813L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String chapter;
	private String content;
	private Integer pageNumber;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	public Page(String chapter, String content, Integer pageNumber, Book book) {
		super();
		this.chapter = chapter;
		this.content = content;
		this.pageNumber = pageNumber;
		this.book = book;
	}

	public Page() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public String getChapter() {
		return chapter;
	}

	public String getContent() {
		return content;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public Book getBook() {
		return book;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", chapter=" + chapter + ", content=" + content + ", pageNumber=" + pageNumber + "]";
	}

}
