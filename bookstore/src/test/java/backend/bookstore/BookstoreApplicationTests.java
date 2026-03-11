package backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import backend.bookstore.web.BookController;
import backend.bookstore.web.BookRestController;

@SpringBootTest
class BookstoreApplicationTests {
	private final BookController bookController;
	private final BookRestController restBookController;

	BookstoreApplicationTests(BookController bookController,
			BookRestController restBookController) {
		this.bookController = bookController;
		this.restBookController = restBookController;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void bookControllerLoad() throws Exception {
		assertThat(bookController).isNotNull();
	}

	@Test
	public void restBookControllerLoad() throws Exception {
		assertThat(restBookController).isNotNull();
	}
}
