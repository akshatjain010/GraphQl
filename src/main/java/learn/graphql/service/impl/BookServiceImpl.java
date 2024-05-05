/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package learn.graphql.service.impl;

import java.util.List; 
import learn.graphql.exception.BookNotFoundException;
import learn.graphql.exception.IllegalArgsException;
import learn.graphql.model.Book;
import learn.graphql.repo.BookRepo;
import learn.graphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Akshat Jain
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepo bookRepo;
    
    @Override
    public Book createBook(Book book) {
        if(book.getTitle().isBlank()) throw new IllegalArgsException("Please fill title");
        if(book.getDesc().isBlank()) throw new IllegalArgsException("Please fill description");
        if(book.getAuthor().isBlank()) throw new IllegalArgsException("Please fill Author");
        if(book.getPages()==0) throw new IllegalArgsException("No pages??");
        if(book.getPrice()==0) throw new IllegalArgsException("No price??");
        return bookRepo.save(book);
    }

    @Override
    public Book findBook(int id) {
        return bookRepo.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Book with this id doesn't exist"));
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepo.findAll();
    }
    
    @Override
    public String deleteBook(int id) {
        Book book= bookRepo.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Book with this id doesn't exist"));
        bookRepo.delete(book);
        return "Book deleted successfully";
    }
    
    @Override
    public Book editBook(int id, Book book) {
        Book newBook= bookRepo.findById(id)
                    .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        newBook.setTitle(book.getTitle());
        newBook.setDesc(book.getDesc());
        newBook.setAuthor(book.getAuthor());
        newBook.setPrice(book.getPrice());
        newBook.setPages(book.getPages());
        return bookRepo.save(newBook);
    }
    
}

