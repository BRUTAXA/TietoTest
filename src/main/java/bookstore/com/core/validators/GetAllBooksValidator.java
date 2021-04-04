package bookstore.com.core.validators;

import bookstore.com.core.request.GetAllBooksRequest;
import bookstore.com.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllBooksValidator {

    public List<CoreError> validate (GetAllBooksRequest getAllBooksRequest){

        return new ArrayList<>();
    }
}
