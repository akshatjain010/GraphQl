package learn.graphql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import learn.graphql.exception.BookNotFoundException;
import learn.graphql.model.Book;
import learn.graphql.repo.BookRepo;
import learn.graphql.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GraphqlApplicationTests {

    @Autowired
    private BookService bookService;
    
    @MockBean
    private BookRepo bookRepo;
    
    @Test
    void contextLoads() {
        
    }
    
    @Test
    void testCreateBook() {
        Book book= new Book();
        
        book.setTitle("Test Title");
        book.setDesc("Test Desc");
        book.setAuthor("Test Author");
        book.setPrice(100.0);
        book.setPages(100);
        when(bookRepo.save(any(Book.class))).thenReturn(book);
        Book createdBook= bookService.createBook(book);
        assertEquals(book, createdBook);
    }
    
    @Test
    void testFindAllBooks() {
        List<Book> books= new ArrayList<>();
        books.add(new Book("Test Title 1", "Test Desc 1", "Test Author 1", 100.0, 100));
        books.add(new Book("Test Title 2", "Test Desc 2", "Test Author 2", 200.0, 200));
        
        when(bookRepo.findAll()).thenReturn(books);
        List<Book> allBooks= bookService.findAllBooks();
        
        assertEquals(books.size(), allBooks.size());
        
        for(int i=0;i<books.size();i++) 
            assertEquals(books.get(i), allBooks.get(i));
    }
    
    @Test
    void testFindBook() {
        Book expectedBook = new Book();
        expectedBook.setTitle("Test Title");
        expectedBook.setDesc("Test Desc");
        expectedBook.setAuthor("Test Author");
        expectedBook.setPrice(100.0);
        expectedBook.setPages(100);
        
        when(bookRepo.findById(anyInt())).thenReturn(Optional.of(expectedBook));
        Book foundBook= bookService.findBook(1);
        
        assertEquals(expectedBook, foundBook);
    }
    
    @Test
    void testDeleteBook() {
        Book book= new Book();
        book.setTitle("Test Title");
        book.setDesc("Test Desc");
        book.setAuthor("Test Author");
        book.setPrice(100.0);
        book.setPages(100);
        
        when(bookRepo.findById(anyInt())).thenReturn(Optional.of(book));
        String result= bookService.deleteBook(2);
        
        assertEquals("Book deleted successfully", result);
        
        verify(bookRepo, times(1)).delete(book);
    }
    
    @Test
    void testEditBook() {
        Book book= new Book();
        book.setTitle("Test Title");
        book.setDesc("Test Desc");
        book.setAuthor("Test Author");
        book.setPrice(100.0);
        book.setPages(100);
        
        when(bookRepo.findById(anyInt())).thenReturn(Optional.of(book));
        Book fetchBook= bookService.findBook(1);
       
        fetchBook.setTitle(book.getTitle());
        fetchBook.setDesc(book.getDesc());
        fetchBook.setAuthor(book.getAuthor());
        fetchBook.setPrice(book.getPrice());
        fetchBook.setPages(book.getPages());
        
        when(bookRepo.save(any(Book.class))).thenReturn(fetchBook);
        Book editedBook= bookService.editBook(1, fetchBook);
        Book updatedBook= bookRepo.findById(1).orElseThrow(()->
        new BookNotFoundException("Book not found with id: "+1));
        assertEquals(editedBook, updatedBook);
    }

}
