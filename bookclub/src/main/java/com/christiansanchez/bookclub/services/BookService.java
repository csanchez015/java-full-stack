package com.christiansanchez.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.christiansanchez.bookclub.models.Book;
import com.christiansanchez.bookclub.repositories.BookRepository;

@Service
public class BookService {
	
	
	private final BookRepository bookRepo;
	
	public BookService(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}
	
	public List<Book> allBooks(){
		return bookRepo.findAll();
	}
	
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}
	
	public Book updateBook(Book book) {
		return bookRepo.save(book);
	}
	
	public void deleteBook(Book book) {
		bookRepo.delete(book);
	}
	
	public Book findBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		}else {
			return null;
		}
	}
}
