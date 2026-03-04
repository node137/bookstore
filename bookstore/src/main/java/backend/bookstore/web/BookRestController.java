package backend.bookstore.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import backend.bookstore.model.Book;
import backend.bookstore.model.BookRepository;

@RestController
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    // Return all books as JSON
    @GetMapping("/books")
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    // Return one book by id as JSON
    @GetMapping("/books/{id}")
    public Optional<Book> getBookById(@PathVariable("id") Long id) {
        return bookRepository.findById(id);
    }
}