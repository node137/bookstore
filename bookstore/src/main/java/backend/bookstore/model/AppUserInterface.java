package backend.bookstore.model;

import org.springframework.data.repository.CrudRepository;

public interface AppUserInterface extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);

}
