package bookstore.com.database;

import bookstore.com.core.domain.Book;

import java.util.List;

public interface BookDatabase {

    void addBook(Book book);

    List<Book> getAllBooks();
}
