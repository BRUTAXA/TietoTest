package bookstore.com.core.validators;

import bookstore.com.core.domain.Book;
import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddBookRequestValidatorTest {

    AddBookRequestValidator validator = new AddBookRequestValidator();

    @Test
    public void testInvalidInputTitle(){
        CoreError expectedError = new CoreError("title", "This field can't be empty");

        Book book = new Book("", "BB");
        AddBookRequest request = new AddBookRequest(book);
        List<CoreError> errors = validator.validate(request);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testInvalidInputAuthor() {
        CoreError expectedError = new CoreError("author", "This field can't be empty");

        Book book = new Book("AAA", "");
        AddBookRequest request = new AddBookRequest(book);
        List<CoreError> errors = validator.validate(request);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        Book book = new Book("AAA", "BBB");
        AddBookRequest request = new AddBookRequest(book);
        List<CoreError> errors = validator.validate(request);

        assertTrue(errors.isEmpty());
    }
}