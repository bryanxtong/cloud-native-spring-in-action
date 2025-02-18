package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("books")
public class BookController {

	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public Flux<Book> get() {
		log.info("Fetching the list of books in the catalog");
		return bookService.viewBookList();
	}

	@GetMapping("{isbn}")
	public Mono<Book> getByIsbn(@PathVariable String isbn) {
		log.info("Fetching the book with ISBN {} from the catalog", isbn);
		return bookService.viewBookDetails(isbn);
	}

	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED)
	public Mono<Book> post(@Valid @RequestBody Book book) {
		log.info("Adding a new book to the catalog with ISBN {}", book.isbn());
		return bookService.addBookToCatalog(book);
	}

	@DeleteMapping("{isbn}")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable String isbn) {
		log.info("Deleting book with ISBN {}", isbn);
		return bookService.removeBookFromCatalog(isbn);
	}

	@PutMapping("{isbn}")
	public Mono<Book> put(@PathVariable String isbn, @Valid @RequestBody Book book) {
		log.info("Updating book with ISBN {}", isbn);
		return bookService.editBookDetails(isbn, book);
	}

}
