package bookstore.com.core.service;


import bookstore.com.core.domain.Book;
import bookstore.com.core.request.GetAllBooksRequest;
import bookstore.com.core.response.CoreError;
import bookstore.com.core.response.GetAllBooksResponse;
import bookstore.com.core.validators.GetAllBooksValidator;
import bookstore.com.database.jpa.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Transactional
public class GetAllBooksService {

    @Autowired private BookRepository bookRepository;
    @Autowired private GetAllBooksValidator getAllBooksValidator;

    public GetAllBooksResponse execute (GetAllBooksRequest getAllBooksRequest){
        List<CoreError> errors = getAllBooksValidator.validate(getAllBooksRequest);

        if (bookRepository.findAll().isEmpty()){
            errors.add(new CoreError("database", "Book database is empty"));
            return new GetAllBooksResponse(errors, new ArrayList<>());
        }

        List<Book> books = bookRepository.findAll();
        return new GetAllBooksResponse(books);
    }
}
