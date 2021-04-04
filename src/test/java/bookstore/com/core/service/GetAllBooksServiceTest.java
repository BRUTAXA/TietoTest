package bookstore.com.core.service;

import bookstore.com.core.domain.Book;
import bookstore.com.core.request.GetAllBooksRequest;
import bookstore.com.core.response.CoreError;
import bookstore.com.core.response.GetAllBooksResponse;
import bookstore.com.core.validators.GetAllBooksValidator;
import bookstore.com.database.jpa.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAllBooksServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private GetAllBooksValidator getAllBooksValidator;
    @InjectMocks
    private GetAllBooksService getAllBooksService;

    @Test
    public void testNoBooksInDatabase(){
        CoreError expectedError= new CoreError("database", "Book database is empty");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        GetAllBooksRequest request = new GetAllBooksRequest();
        Mockito.when(getAllBooksValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(bookRepository.findAll()).thenReturn(new ArrayList<>());

        GetAllBooksResponse response = getAllBooksService.execute(request);

        assertTrue(response.hasErrors());
        assertFalse(response.getErrors().contains(expectedError));
        assertTrue(response.getErrors().size() == 1);
    }

    @Test
    public void testReturnListOfBooks(){
        Book book = new Book("AAA", "BBB");
        List<Book> books = new ArrayList<>();
        books.add(book);

        GetAllBooksRequest request = new GetAllBooksRequest();
        Mockito.when(getAllBooksValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(bookRepository.findAll()).thenReturn(books);

        GetAllBooksResponse response = getAllBooksService.execute(request);

        assertFalse(response.hasErrors());
        assertTrue(response.getBooks().equals(books));
    }
}