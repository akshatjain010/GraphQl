/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package learn.graphql.controller;

import java.util.List;
import learn.graphql.model.Book;
import learn.graphql.service.BookService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Akshat Jain
 */
@Controller
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @MutationMapping("deleteBook")
    public String deleteBook(@Argument int id) {
        return bookService.deleteBook(id);
    }
    
    @MutationMapping("createBook")
    public Book createBook(@Argument BookInput book) {
        Book b= new Book();
        b.setTitle(book.getTitle());
        b.setDesc(book.getDesc());
        b.setAuthor(book.getAuthor());
        b.setPrice(book.getPrice());
        b.setPages(book.getPages());
        return bookService.createBook(b);
    }
    
    @MutationMapping("editBook")
    public Book editBook(@Argument int id, @Argument BookInput book) {
        Book b= new Book();
        b.setTitle(book.getTitle());
        b.setDesc(book.getDesc());
        b.setAuthor(book.getAuthor());
        b.setPrice(book.getPrice());
        b.setPages(book.getPages());
        return bookService.editBook(id, b);
    }
    
    @QueryMapping("findAllBooks")
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }
    
    @QueryMapping("findBook")
    public Book findBook(@Argument int id) {
        return bookService.findBook(id);
    }
}

@Getter
@Setter
class BookInput {

    private String title;
    private String desc;
    private String author;
    private double price;
    private int pages;
}
