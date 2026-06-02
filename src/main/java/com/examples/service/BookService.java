package com.examples.service;

import com.examples.DemoApplication;
import com.examples.repository.BookRepository;
import com.examples.resource.Book;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    private static final Logger log =  LoggerFactory.getLogger(BookService.class);

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public Optional<Book> findByIdWithPages(Long id) {
        return repo.findById(id).map(book -> {
            // Option 1: access the collection to initialize it
            log.debug("book '{}' has {} pages", id, book.getPages().size());
            // Option 2: explicitly initialize via Hibernate
//             Hibernate.initialize(book.getPages());
            return book;
        });
    }
}
