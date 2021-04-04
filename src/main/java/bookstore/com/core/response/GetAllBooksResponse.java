package bookstore.com.core.response;

import bookstore.com.core.domain.Book;

import java.util.List;

public class GetAllBooksResponse {

    private List<Book> books;

    public GetAllBooksResponse(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
