package backend.bookstore;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.model.BookRepository;
import backend.bookstore.model.Book;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository) {
		return (args) -> {
			// Your code...add some demo data to db
			log.info("saving some books at  initialization");
			bookRepository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "978-0743273565", 10.99));
			bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "978-0061120084", 7.99));
			bookRepository.save(new Book("1984", "George Orwell", 1949, "978-0451524935", 8.99));
			log.info("fetch all books");
			for (Book kirja : bookRepository.findAll()) {
				log.info(kirja.toString());
			}
		};
	}
}
