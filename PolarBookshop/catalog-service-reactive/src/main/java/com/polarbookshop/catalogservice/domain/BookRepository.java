package com.polarbookshop.catalogservice.domain;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

public interface BookRepository extends ReactiveCrudRepository<Book,Long> {

	Mono<Book> findByIsbn(String isbn);

	Mono<Boolean> existsByIsbn(String isbn);

	@Modifying
	@Transactional
	@Query("delete from Book where isbn = :isbn")
	Mono<Void> deleteByIsbn(String isbn);

}
