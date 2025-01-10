package com.johndeere.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.johndeere.resource.Book;
import com.johndeere.resource.Page;

public interface PageRepository extends CrudRepository<Page, Long> {

	List<Page> findByBook(Book book, Sort sort);
}
