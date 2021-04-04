package bookstore.com.core.request;

import bookstore.com.core.domain.Book;

public class AddBookRequest {

   private Book book;

    public AddBookRequest(Book book) {
        this.book = book;
    }

    public AddBookRequest() { }

    public Book getBook(){
        return book;
    }

    public void setBook(Book book){
        this.book = book;
    }
}
