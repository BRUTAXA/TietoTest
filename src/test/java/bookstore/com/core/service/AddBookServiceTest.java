package bookstore.com.core.service;

import bookstore.com.core.domain.Book;
import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.response.AddBookResponse;
import bookstore.com.core.response.CoreError;
import bookstore.com.core.validators.AddBookRequestValidator;
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
public class AddBookServiceTest {

    @Mock private BookRepository bookRepository;
    @Mock private AddBookRequestValidator addBookRequestValidator;
    @InjectMocks private AddBookService addBookService;

    @Test
    public void testNotValidTitleRequest(){
        Book book = new Book("","J.K.Rowling");
        AddBookRequest request = new AddBookRequest(book);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("title", "This field can't be empty");
        errors.add(expectedError);
        Mockito.when(addBookRequestValidator.validate(request)).thenReturn(errors);

        AddBookResponse response = addBookService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().contains(expectedError), true);

        Mockito.verifyNoInteractions(bookRepository);
    }

    @Test
    public void testNotValidAuthorRequest(){
        Book book = new Book("AAA", "");
        AddBookRequest request = new AddBookRequest(book);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("author", "This field can't be empty");
        errors.add(expectedError);
        Mockito.when(addBookRequestValidator.validate(request)).thenReturn(errors);

        AddBookResponse response = addBookService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().contains(expectedError), true);
    }

    @Test
    public void testNotValidAllFieldsEmptyRequest(){
        Book book = new Book(null, "");
        AddBookRequest request = new AddBookRequest(book);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError1 = new CoreError("title", "This field can't be empty");
        CoreError expectedError2 = new CoreError("author", "This field can't be empty");
        errors.add(expectedError1);
        errors.add(expectedError2);
        Mockito.when(addBookRequestValidator.validate(request)).thenReturn(errors);

        AddBookResponse response = addBookService.execute(request);
        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().size() == 2);
        assertTrue(response.getErrors().contains(expectedError1));
        assertTrue(response.getErrors().contains(expectedError2));
        Mockito.verifyNoInteractions(bookRepository);
    }

    @Test
    public void testBookAddedSuccessfully(){
        Book book = new Book("AAA", "BBB");
        AddBookRequest request = new AddBookRequest(book);
        Mockito.when(addBookRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(bookRepository.exists(request.getBook())).thenReturn(false);

        AddBookResponse response = addBookService.execute(request);
        assertTrue(!response.hasErrors());
        assertTrue(response.getNewBook().equals(book));
    }
}