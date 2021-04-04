package bookstore.com.console_ui;

import bookstore.com.core.domain.Book;
import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.response.AddBookResponse;
import bookstore.com.core.service.AddBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class AddBookUIAction implements UIAction{

    @Autowired private AddBookService service;

    @Override
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book title: ");
        String bookTitle = scanner.nextLine();
        System.out.println("Enter book's author: ");
        String bookAuthor = scanner.nextLine();

        Book newBook = new Book(bookTitle, bookAuthor);
        AddBookRequest request = new AddBookRequest(newBook);
        AddBookResponse response = service.execute(request);

        if (response.hasErrors()){
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("New book id is: " + response.getNewBook().getId());
            System.out.println("Your book was added to list.");
        }
    }
}


