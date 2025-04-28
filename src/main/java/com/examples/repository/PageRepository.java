package com.examples.repository;

import com.examples.resource.Book;
import com.examples.resource.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PageRepository extends CrudRepository<Page, Long> {

	List<Page> findByBook(Book book, Sort sort);
}
