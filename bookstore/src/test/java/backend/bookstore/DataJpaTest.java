package backend.bookstore;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;

//@DataJpaTest
@ActiveProfiles("test")
class DataJpaTest {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    DataJpaTest(BookRepository bookRepository,
            CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Test
    void createBook() {
        Category category = new Category("TestCategory");
        categoryRepository.save(category);

        Book book = new Book("Test Book", "Tester", 2024, category);
        bookRepository.save(book);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    void searchBook() {
        List<Book> books = bookRepository.findByTitle("Puutarha");
        assertThat(books).isNotEmpty();
    }

    @Test
    void deleteBook() {
        List<Book> books = bookRepository.findByTitle("Puutarha");
        Book book = books.get(0);

        bookRepository.delete(book);

        assertThat(bookRepository.findByTitle("Puutarha")).isEmpty();
    }
}