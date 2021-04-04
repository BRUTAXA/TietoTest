package bookstore.com.core.service;


import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.response.AddBookResponse;
import bookstore.com.core.response.CoreError;
import bookstore.com.core.validators.AddBookRequestValidator;
import bookstore.com.database.jpa.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Component
@Transactional
public class AddBookService {

    @Autowired private BookRepository bookRepository;
    @Autowired private AddBookRequestValidator addBookRequestValidator;

    public AddBookResponse execute (AddBookRequest addBookRequest) {

        List<CoreError> errors = addBookRequestValidator.validate(addBookRequest);
        if (!errors.isEmpty()){
            return new AddBookResponse(errors);
        }

        bookRepository.save(addBookRequest.getBook());
        return new AddBookResponse(addBookRequest.getBook());
    }


}
