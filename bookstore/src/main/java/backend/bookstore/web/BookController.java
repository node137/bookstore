package backend.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import backend.bookstore.model.Book;
import backend.bookstore.model.BookRepository;

@Controller
public class BookController {
    private BookRepository bookRepository;

    // constructor injection. Can only be one constructor
    public BookController(BookRepository repository) {
        this.bookRepository = repository;
    }

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "Welcome to Bookstore!";
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "/books";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook"; // addbook.html
    }

    @PostMapping("/saveBook")
    public String saveBook(Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).get());
        return "editbook";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBooks(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}