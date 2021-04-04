package bookstore.com.core.validators;

import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AddBookRequestValidator {

    public List<CoreError> validate(AddBookRequest request) {
        List<CoreError> errors = new ArrayList<>();

        errors.addAll(titleValidationErrors(request.getBook().getTitle()));
        errors.addAll(authorValidationErrors(request.getBook().getAuthor()));
        return errors;
    }

    private List<CoreError> titleValidationErrors(String title){
        List<CoreError> errors = new ArrayList<>();
        if (title == null || title.isEmpty()){
            errors.add(new CoreError("title", "This field can't be empty"));
        }
        return errors;
    }

    private List<CoreError> authorValidationErrors(String author) {
        List<CoreError> errors = new ArrayList<>();
        if (author == null || author.isEmpty()){
            errors.add(new CoreError("author", "This field can't be empty"));
        }
        if (!containsOnlyLetters(author)){
            errors.add(new CoreError("author", "Not valid input for author, can contain only letters"));
        }
        return errors;
    }

    private boolean containsOnlyLetters(String input){
        String regex = "[a-zA-Z]+";

        Pattern pattern = Pattern.compile(regex);
        if (input == null){
            return false;
        }
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
