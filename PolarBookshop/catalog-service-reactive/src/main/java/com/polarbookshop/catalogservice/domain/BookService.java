package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flux<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Mono<Book> viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .switchIfEmpty(Mono.error(new BookNotFoundException(isbn)));
    }

    public Mono<Book> addBookToCatalog(Book book) {
        return bookRepository.existsByIsbn(book.isbn()).flatMap(
                exist -> {
                    if (exist) {
                        return Mono.error(new BookAlreadyExistsException(book.isbn()));
                    }
                    return bookRepository.save(book);
                });
    }

    public Mono<Void> removeBookFromCatalog(String isbn) {
        return bookRepository.deleteByIsbn(isbn);
    }

    public Mono<Book> editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn).log()
                .flatMap(existingBook -> {
                    var bookToUpdate = new Book(
                            existingBook.id(),
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price(),
                            book.publisher(),
                            existingBook.createdDate(),
                            existingBook.lastModifiedDate(),
                            existingBook.createdBy(),
                            existingBook.lastModifiedBy(),
                            existingBook.version());
                    return bookRepository.save(bookToUpdate);
                }).switchIfEmpty(addBookToCatalog(book));
    }

}
