package com.examples.service;

import com.examples.repository.BookRepository;
import com.examples.repository.PageRepository;
import com.examples.resource.Book;
import com.examples.resource.Page;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PageService {

    private final PageRepository repo;

    public PageService(PageRepository repo) {
        this.repo = repo;
    }

//    @Transactional(readOnly = true)
//    public Optional<Page> findByBook(Book book) {
//        return repo.findByBook(book).map(pagee -> {
//            // Option 1: access the collection to initialize it
////            book.getPages().size();
//            // Option 2: explicitly initialize via Hibernate
//             Hibernate.initialize(book.getPages());
//            return book;
//        });
//    }
}
