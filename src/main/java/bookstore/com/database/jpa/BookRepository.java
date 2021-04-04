package bookstore.com.database.jpa;

import bookstore.com.core.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

    boolean exists(Book book);
}
