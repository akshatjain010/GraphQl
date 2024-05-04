/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package learn.graphql.service;

import java.util.List;
import learn.graphql.model.Book;

/**
 *
 * @author Akshat Jain
 */
public interface BookService {
    
    Book createBook(Book book);
    
    Book findBook(int id);
    
    List<Book> findAllBooks(); 
    
    String deleteBook(int id);
    
    Book editBook(int id, Book book);
}
