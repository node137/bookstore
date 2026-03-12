package backend.bookstore;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Autowired
	private CategoryRepository categoryRepository;

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository) {
		return (args) -> {
			// Your code...add some demo data to db
			// log.info("saving some books at initialization");
			bookRepository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "978-0743273565", 10.99));
			bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "978-0061120084", 7.99));
			bookRepository.save(new Book("1984", "George Orwell", 1949, "978-0451524935", 8.99));
			categoryRepository.save(new Category("Fiktio"));
			categoryRepository.save(new Category("Tiedekirjallisuus"));
			categoryRepository.save(new Category("Fantasia"));
			categoryRepository.save(new Category("Historia"));

			// log.info("fetch all books");
			for (Book kirja : bookRepository.findAll()) {
				log.info(kirja.toString());
			}
		};
	}
}
