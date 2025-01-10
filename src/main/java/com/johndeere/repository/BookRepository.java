package com.johndeere.repository;

import org.springframework.data.repository.CrudRepository;

import com.johndeere.resource.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
