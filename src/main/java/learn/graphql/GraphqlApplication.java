package learn.graphql;

import learn.graphql.model.Book;
import learn.graphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphqlApplication implements CommandLineRunner{

    @Autowired
    private BookService bookService;
    
    @Override
    public void run(String... args) throws Exception {
        
        Book b1= new Book();
        b1.setTitle("Java");
        b1.setDesc("Hello Java");
        b1.setAuthor("Akshat");
        b1.setPrice(200);
        b1.setPages(100);
        
        Book b2= new Book();
        b2.setTitle("Python");
        b2.setDesc("Hello Python");
        b2.setAuthor("Shruti");
        b2.setPrice(300);
        b2.setPages(150);
        
        Book b3= new Book();
        b3.setTitle("C++");
        b3.setDesc("Hello C++");
        b3.setAuthor("Swati");
        b3.setPrice(300);
        b3.setPages(150);
        
        bookService.createBook(b1);
        bookService.createBook(b2);
        bookService.createBook(b3);
    }

    public static void main(String[] args) {
	SpringApplication.run(GraphqlApplication.class, args);
    }
        
}
