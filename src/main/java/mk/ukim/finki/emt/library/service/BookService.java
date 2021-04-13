package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.dto.BookDto;
import mk.ukim.finki.emt.library.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, Category category, Integer availableCopies, Long authorId);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long bookId, String name, Category category, Integer availableCopies, Long authorId);

    void deleteBook(Long id);

    void markAsTaken(Long id);

}
